package strategy.scoringrulestrategy.impl;

import strategy.scoringrulestrategy.DiceScoringRule;
import utils.YatzyUtils;

import static utils.YatzyUtils.counts;

public class PairScoringRule implements DiceScoringRule {
    private final int pairValue;

    public PairScoringRule(int pairValue) {
        this.pairValue = pairValue;
    }

    @Override
    public int score(int... dice) {
        return scoreOfAKind(pairValue, counts(dice));
    }


    private int scoreOfAKind(int pairValue, int[] counts) {
        if (pairValue == 2) {
            return twoPair(counts);
        }

        int pair = (pairValue == 1) ? 2 : pairValue;

        for (int face = 6; face >= 1; face--) {
            if (counts[face - 1] >= pair) {
                return face * pair; // highest pair
            }
        }
        return 0;
    }

    public static int twoPair(int[] counts) {
        int pairsFound = 0;
        int total = 0;

        for (int face = 6; face >= 1; face--) {
            if (counts[face - 1] >= 2) {
                pairsFound++;
                total += face * 2;

                if (pairsFound == 2) {
                    return total;
                }
            }
        }
        return 0;
    }
}