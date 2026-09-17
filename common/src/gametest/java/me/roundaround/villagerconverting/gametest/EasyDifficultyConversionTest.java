package me.roundaround.villagerconverting.gametest;

import me.roundaround.allay.api.gametest.ServerGameTest;
import me.roundaround.trove.gametest.ServerTest;
import me.roundaround.trove.gametest.ServerTestContext;
import net.minecraft.world.Difficulty;

import static me.roundaround.villagerconverting.gametest.ConversionTestSupport.check;
import static me.roundaround.villagerconverting.gametest.ConversionTestSupport.configure;
import static me.roundaround.villagerconverting.gametest.ConversionTestSupport.countConversions;
import static me.roundaround.villagerconverting.gametest.ConversionTestSupport.prepare;

/**
 * Vanilla never converts on Easy, so every conversion here is the mod: the difficulty override
 * gets the kill past the Normal/Hard gate and the coin-flip override makes it certain.
 */
@ServerGameTest
public class EasyDifficultyConversionTest implements ServerTest {
  private static final int TRIALS = 32;

  @Override
  public void runTest(ServerTestContext context) {
    prepare(context);
    configure(context, Difficulty.EASY, true, false);
    int conversions = countConversions(context, TRIALS, false);
    check(conversions == TRIALS, "expected every Easy kill to convert but got " + conversions + "/" + TRIALS);
  }
}
