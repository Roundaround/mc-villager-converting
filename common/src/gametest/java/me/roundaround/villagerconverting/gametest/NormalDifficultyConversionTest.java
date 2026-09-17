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
 * Vanilla converts half the time on Normal; with the mod every kill must convert. A vanilla
 * coin flip surviving {@code TRIALS} straight kills is a 1-in-2^32 false pass.
 */
@ServerGameTest
public class NormalDifficultyConversionTest implements ServerTest {
  private static final int TRIALS = 32;

  @Override
  public void runTest(ServerTestContext context) {
    prepare(context);
    configure(context, Difficulty.NORMAL, true, false);
    int conversions = countConversions(context, TRIALS, false);
    check(conversions == TRIALS, "expected every Normal kill to convert but got " + conversions + "/" + TRIALS);
  }
}
