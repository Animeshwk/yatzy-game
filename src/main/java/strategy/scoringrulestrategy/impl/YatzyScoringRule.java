package strategy.scoringrulestrategy.impl;

import strategy.scoringrulestrategy.DiceScoringRule;

public class YatzyScoringRule implements DiceScoringRule {

    public YatzyScoringRule() {}

    @Override
    public int score(int... dice) {
        if (dice == null || dice.length != 5) {
            return 0;
        }

        int firstDie = dice[0];
        for (int i = 1; i < dice.length; i++) {
            if (dice[i] != firstDie) {
                return 0;
            }
        }
        return 50;
    }
}
