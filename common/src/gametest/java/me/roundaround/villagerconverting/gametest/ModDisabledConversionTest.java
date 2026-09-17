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
 * With {@code modEnabled} off both injectors must fall through to vanilla: never on Easy, and a
 * genuine coin flip on Normal (all-or-nothing over {@code TRIALS} kills is a 1-in-2^31 flake).
 */
@ServerGameTest
public class ModDisabledConversionTest implements ServerTest {
  private static final int TRIALS = 32;

  @Override
  public void runTest(ServerTestContext context) {
    prepare(context);
    configure(context, Difficulty.EASY, false, false);
    int easy = countConversions(context, TRIALS, false);
    check(easy == 0, "expected no Easy conversions with the mod off but got " + easy + "/" + TRIALS);

    configure(context, Difficulty.NORMAL, false, false);
    int normal = countConversions(context, TRIALS, false);
    check(normal > 0 && normal < TRIALS,
        "expected a vanilla coin flip on Normal with the mod off but got " + normal + "/" + TRIALS);
  }
}
