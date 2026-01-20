package strategy.scoringrulestrategy.impl;

import strategy.scoringrulestrategy.DiceScoringRule;
import utils.YatzyUtils;

public class StraightScoringRule implements DiceScoringRule {

    private final int startFace;

    public StraightScoringRule(int startFace) {
        this.startFace = startFace;
    }

    @Override
    public int score(int... dice) {

        // startFace=1 => checks 1..5
        if(startFace == 1) {
            return smallStraight(dice);
        }

        // startFace=2 => checks 2..6
        if(startFace == 2) {
            return largeStraight(dice);
        }
        return 0;
    }

    private int smallStraight(int... dice) {
        int[] counts = YatzyUtils.counts(dice);

        // startFace=1 => checks 1..5
        return isStraight(startFace, counts) ? 15 : 0;
    }

    private int largeStraight(int... dice) {
        int[] counts = YatzyUtils.counts(dice);

        // startFace=2 => checks 2..6
        return isStraight(startFace, counts) ? 20 : 0;
    }

    private static boolean isStraight(int startFace, int[] counts) {
        for (int face = startFace; face < startFace + 5; face++) {
            if (counts[face - 1] != 1) {
                return false;
            }
        }
        return true;
    }
}
