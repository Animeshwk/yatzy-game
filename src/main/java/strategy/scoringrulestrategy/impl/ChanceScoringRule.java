package strategy.scoringrulestrategy.impl;

import strategy.scoringrulestrategy.DiceScoringRule;

import java.util.Arrays;

public class ChanceScoringRule implements DiceScoringRule {

    public ChanceScoringRule() {}

    @Override
    public int score(int... dice) {
        return Arrays.stream(dice).sum();
    }
}
