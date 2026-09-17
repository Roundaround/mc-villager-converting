package me.roundaround.villagerconverting.gametest;

import me.roundaround.allay.api.gametest.ServerGameTest;
import me.roundaround.trove.gametest.ServerTest;
import me.roundaround.trove.gametest.ServerTestContext;
import net.minecraft.world.Difficulty;

import static me.roundaround.villagerconverting.gametest.ConversionTestSupport.check;
import static me.roundaround.villagerconverting.gametest.ConversionTestSupport.configure;
import static me.roundaround.villagerconverting.gametest.ConversionTestSupport.countConversions;
import static me.roundaround.villagerconverting.gametest.ConversionTestSupport.prepare;

/** Hard already always converts in vanilla; the mod must leave that intact whether enabled or not. */
@ServerGameTest
public class HardDifficultyConversionTest implements ServerTest {
  private static final int TRIALS = 32;

  @Override
  public void runTest(ServerTestContext context) {
    prepare(context);
    configure(context, Difficulty.HARD, true, false);
    int enabled = countConversions(context, TRIALS, false);
    check(enabled == TRIALS, "expected every Hard kill to convert but got " + enabled + "/" + TRIALS);

    configure(context, Difficulty.HARD, false, false);
    int disabled = countConversions(context, TRIALS, false);
    check(disabled == TRIALS, "expected every Hard kill to convert with the mod off but got " + disabled + "/" + TRIALS);
  }
}
