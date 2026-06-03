package me.roundaround.villagerconverting.forge;

import me.roundaround.trove.forge.TroveForge;
import me.roundaround.villagerconverting.client.gui.screen.NotInWorldConfigScreen;
import me.roundaround.villagerconverting.config.VillagerConvertingConfig;
import me.roundaround.villagerconverting.generated.Constants;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("villagerconverting")
public final class VillagerConvertingForgeMod {
  public VillagerConvertingForgeMod(FMLJavaModLoadingContext context) {
    TroveForge.bootstrap(context);
    VillagerConvertingConfig.getInstance().init();

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
