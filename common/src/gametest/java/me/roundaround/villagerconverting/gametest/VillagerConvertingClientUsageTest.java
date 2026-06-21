package me.roundaround.villagerconverting.gametest;

import me.roundaround.allay.api.gametest.ClientGameTest;
import me.roundaround.villagerconverting.client.gui.screen.NotInWorldConfigScreen;
import me.roundaround.trove.gametest.ClientTest;
import me.roundaround.trove.gametest.ClientTestContext;

/**
 * Opens the mod's out-of-world config screen from the title screen and asserts it
 * renders. The only client surface the mod has, it builds a Trove layout in
 * {@code init()}, so this also exercises the 26.2 GUI-render path.
 */
@ClientGameTest
public class VillagerConvertingClientUsageTest implements ClientTest {
  @Override
  public void runTest(ClientTestContext context) {
    context.setScreen(() -> new NotInWorldConfigScreen(null));
    context.assertScreen(NotInWorldConfigScreen.class);
    context.waitTicks(2);
    context.returnToTitle();
  }
}
