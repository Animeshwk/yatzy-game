package strategy.scoringrulestrategy.impl;

import strategy.scoringrulestrategy.DiceScoringRule;

public class DiceFaceScoringRule implements DiceScoringRule {
    private final int face;

    public DiceFaceScoringRule(int face) {
        this.face = face;
    }

    @Override
    public int score(int... dice) {
        int sum = 0;

        for (int die : dice) {
            if (die == face) {
                sum += face;
            }
        }
        return sum;
    }
}
