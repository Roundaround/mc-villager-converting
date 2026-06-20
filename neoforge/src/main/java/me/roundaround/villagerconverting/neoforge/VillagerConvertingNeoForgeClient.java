package me.roundaround.villagerconverting.neoforge;

import me.roundaround.villagerconverting.client.gui.screen.NotInWorldConfigScreen;
import me.roundaround.villagerconverting.config.VillagerConvertingConfig;
import me.roundaround.villagerconverting.generated.Constants;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

// Separate class (not an inline Dist gate in the @Mod ctor) so the dedicated server never links its client classes.
public final class VillagerConvertingNeoForgeClient {
  private VillagerConvertingNeoForgeClient() {
  }

  public static void init(ModContainer container) {
    container.registerExtensionPoint(IConfigScreenFactory.class, (modContainer, parent) -> {
      VillagerConvertingConfig config = VillagerConvertingConfig.getInstance();
      if (!config.isReady()) {
        return new NotInWorldConfigScreen(parent);
      }
      return new me.roundaround.trove.client.gui.screen.ConfigScreen(parent, Constants.MOD_ID, config);
    });
  }
}
