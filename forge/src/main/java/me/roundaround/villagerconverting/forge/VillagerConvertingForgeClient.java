package me.roundaround.villagerconverting.forge;

import me.roundaround.villagerconverting.client.gui.screen.NotInWorldConfigScreen;
import me.roundaround.villagerconverting.config.VillagerConvertingConfig;
import me.roundaround.villagerconverting.generated.Constants;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// Separate class (not an inline Dist gate in the @Mod ctor) so the dedicated server never links its client classes.
public final class VillagerConvertingForgeClient {
  private VillagerConvertingForgeClient() {
  }

  public static void init(FMLJavaModLoadingContext context) {
    context.getContainer().registerExtensionPoint(
        ConfigScreenHandler.ConfigScreenFactory.class,
        () -> new ConfigScreenHandler.ConfigScreenFactory(
            (mc, parent) -> {
              VillagerConvertingConfig config = VillagerConvertingConfig.getInstance();
              if (!config.isReady()) {
                return new NotInWorldConfigScreen(parent);
              }
              return new me.roundaround.trove.client.gui.screen.ConfigScreen(parent, Constants.MOD_ID, config);
            }));
  }
}
