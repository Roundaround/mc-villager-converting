package me.roundaround.villagerconverting.gametest;

import me.roundaround.trove.config.option.BooleanConfigOption;
import me.roundaround.trove.gametest.GameTestAssertionException;
import me.roundaround.trove.gametest.ServerTestContext;
import me.roundaround.villagerconverting.config.VillagerConvertingConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.monster.zombie.ZombieVillager;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.phys.AABB;

import java.util.List;

/** Shared helpers for the Villager Converting {@code @ServerGameTest} suite. */
final class ConversionTestSupport {
  private static final BlockPos ARENA = new BlockPos(8, 200, 8);

  private ConversionTestSupport() {
  }

  /**
   * Snapshot the difficulty and config (restored at teardown) and force-load the arena chunk,
   * since a player-less dedicated server keeps nothing loaded. Call once, before {@link #configure}.
   */
  static void prepare(ServerTestContext context) {
    VillagerConvertingConfig config = VillagerConvertingConfig.getInstance();
    boolean wasEnabled = config.modEnabled.getValue();
    boolean wasRequireName = config.requireName.getValue();
    Difficulty wasDifficulty = context.computeOnServer((server) -> server.overworld().getDifficulty());
    ChunkPos chunk = ChunkPos.containing(ARENA);

    context.onCleanup(() -> {
      setConfig(config.modEnabled, wasEnabled);
      setConfig(config.requireName, wasRequireName);
      context.runOnServer((server) -> {
        server.setDifficulty(wasDifficulty, true);
        server.overworld().setChunkForced(chunk.x(), chunk.z(), false);
      });
    });

    context.runOnServer((server) -> server.overworld().setChunkForced(chunk.x(), chunk.z(), true));
    for (int i = 0; i < 100; i++) {
      if (context.computeOnServer((server) -> server.overworld().isPositionEntityTicking(ARENA))) {
        return;
      }
      context.waitTicks(1);
    }
    throw new GameTestAssertionException("arena chunk never became entity-ticking");
  }

  /** Set the difficulty and both config options (in memory only) for the kills that follow. */
  static void configure(ServerTestContext context, Difficulty difficulty, boolean modEnabled, boolean requireName) {
    VillagerConvertingConfig config = VillagerConvertingConfig.getInstance();
    setConfig(config.modEnabled, modEnabled);
    setConfig(config.requireName, requireName);
    context.runOnServer((server) -> server.setDifficulty(difficulty, true));
  }

  /**
   * Have a zombie land the killing blow on a fresh villager and report whether the villager came
   * back as a zombie villager. Runs in a single server-thread hop so no tick (AI, sunburn,
   * gravity) interleaves, and clears the arena afterwards so trials are independent.
   */
  static boolean zombieKillsVillager(ServerTestContext context, boolean named) {
    return context.computeOnServer((server) -> {
      ServerLevel level = server.overworld();
      AABB arena = new AABB(ARENA).inflate(3.0);
      level.getEntitiesOfClass(ZombieVillager.class, arena).forEach(ZombieVillager::discard);

      Zombie zombie = EntityTypes.ZOMBIE.create(level, EntitySpawnReason.COMMAND);
      Villager villager = EntityTypes.VILLAGER.create(level, EntitySpawnReason.COMMAND);
      if (zombie == null || villager == null) {
        throw new GameTestAssertionException("could not create the test zombie/villager");
      }
      zombie.setPos(ARENA.getX() + 0.5, ARENA.getY(), ARENA.getZ() + 0.5);
      villager.setPos(ARENA.getX() + 1.5, ARENA.getY(), ARENA.getZ() + 0.5);
      if (named) {
        villager.setCustomName(Component.literal("Test Subject"));
      }
      if (!level.addFreshEntity(zombie) || !level.addFreshEntity(villager)) {
        throw new GameTestAssertionException("could not add the test zombie/villager to the overworld");
      }

      villager.hurtServer(level, level.damageSources().mobAttack(zombie), Float.MAX_VALUE);
      if (villager.isAlive()) {
        throw new GameTestAssertionException("the zombie's attack did not kill the villager");
      }

      List<ZombieVillager> converted = level.getEntitiesOfClass(ZombieVillager.class, arena);
      converted.forEach(ZombieVillager::discard);
      zombie.discard();
      villager.discard();

      if (converted.size() > 1) {
        throw new GameTestAssertionException("one villager produced " + converted.size() + " zombie villagers");
      }
      return !converted.isEmpty();
    });
  }

  /** Run {@code trials} independent kills and return how many converted. */
  static int countConversions(ServerTestContext context, int trials, boolean named) {
    int conversions = 0;
    for (int i = 0; i < trials; i++) {
      if (zombieKillsVillager(context, named)) {
        conversions++;
      }
    }
    return conversions;
  }

  static void check(boolean condition, String message) {
    if (!condition) {
      throw new GameTestAssertionException(message);
    }
  }

  private static void setConfig(BooleanConfigOption option, boolean value) {
    option.setValue(value);
    option.commit();
    if (option.getValue() != value) {
      throw new GameTestAssertionException("config option " + option.getPath() + " did not take value " + value);
    }
  }
}
