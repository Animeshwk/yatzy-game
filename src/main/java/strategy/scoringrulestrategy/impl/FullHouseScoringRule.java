package strategy.scoringrulestrategy.impl;

import strategy.scoringrulestrategy.DiceScoringRule;
import utils.YatzyUtils;

public class FullHouseScoringRule implements DiceScoringRule {

    public FullHouseScoringRule() {}

    @Override
    public int score(int... dice) {
        return fullHouse(dice);
    }

    private int fullHouse(int... dice) {
        int[] counts = YatzyUtils.counts(dice);

        int pairFace = 0;
        int tripleFace = 0;

        for (int face = 1; face <= 6; face++) {
            int count = counts[face - 1];

            if (count == 2) {
                pairFace = face;
            }

            if (count == 3) {
                tripleFace = face;
            }
        }

        return (pairFace != 0 && tripleFace != 0)
                ? pairFace * 2 + tripleFace * 3
                : 0;
    }
}
