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
 * With {@code requireName} on, only name-tagged villagers get the guarantee; unnamed ones fall
 * back to vanilla (never on Easy, a coin flip on Normal).
 */
@ServerGameTest
public class RequireNameConversionTest implements ServerTest {
  private static final int TRIALS = 32;

  @Override
  public void runTest(ServerTestContext context) {
    prepare(context);
    configure(context, Difficulty.EASY, true, true);
    int easyNamed = countConversions(context, TRIALS, true);
    check(easyNamed == TRIALS, "expected every named Easy kill to convert but got " + easyNamed + "/" + TRIALS);
    int easyUnnamed = countConversions(context, TRIALS, false);
    check(easyUnnamed == 0, "expected no unnamed Easy conversions but got " + easyUnnamed + "/" + TRIALS);

    configure(context, Difficulty.NORMAL, true, true);
    int normalNamed = countConversions(context, TRIALS, true);
    check(normalNamed == TRIALS, "expected every named Normal kill to convert but got " + normalNamed + "/" + TRIALS);
    int normalUnnamed = countConversions(context, TRIALS, false);
    check(normalUnnamed > 0 && normalUnnamed < TRIALS,
        "expected a vanilla coin flip for unnamed Normal kills but got " + normalUnnamed + "/" + TRIALS);
  }
}
