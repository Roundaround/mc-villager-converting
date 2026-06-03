package me.roundaround.villagerconverting.neoforge;

import me.roundaround.trove.neoforge.TroveNeoForge;
import me.roundaround.villagerconverting.client.gui.screen.NotInWorldConfigScreen;
import me.roundaround.villagerconverting.config.VillagerConvertingConfig;
import me.roundaround.villagerconverting.generated.Constants;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod("villagerconverting")
public final class VillagerConvertingNeoForgeMod {
  public VillagerConvertingNeoForgeMod(IEventBus modBus, ModContainer container) {
    TroveNeoForge.bootstrap(modBus, container);
    VillagerConvertingConfig.getInstance().init();

    container.registerExtensionPoint(IConfigScreenFactory.class, (modContainer, parent) -> {
      VillagerConvertingConfig config = VillagerConvertingConfig.getInstance();
      if (!config.isReady()) {
        return new NotInWorldConfigScreen(parent);
      }
      return new me.roundaround.trove.client.gui.screen.ConfigScreen(parent, Constants.MOD_ID, config);
    });
  }
}
