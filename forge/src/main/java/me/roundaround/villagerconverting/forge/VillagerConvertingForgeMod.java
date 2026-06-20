package me.roundaround.villagerconverting.forge;

import me.roundaround.trove.forge.TroveForge;
import me.roundaround.villagerconverting.config.VillagerConvertingConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod("villagerconverting")
public final class VillagerConvertingForgeMod {
  public VillagerConvertingForgeMod(FMLJavaModLoadingContext context) {
    TroveForge.bootstrap(context);
    VillagerConvertingConfig.getInstance().init();

    // Client setup lives in VillagerConvertingForgeClient (separate class, not inline) so the dedicated server never links its client classes.
    if (FMLEnvironment.dist.isClient()) {
      VillagerConvertingForgeClient.init(context);
    }
  }
}
