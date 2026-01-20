package strategy.scoringrulestrategy.impl;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.scoringrulestrategy.DiceScoringRule;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChanceScoringRuleTest {

    private final DiceScoringRule rule = new ChanceScoringRule();

    @Test
    @DisplayName("score() should return sum of all dice")
    void score_shouldReturnSum() {
        assertEquals(15, rule.score(1, 2, 3, 4, 5));
        assertEquals(18, rule.score(6, 6, 2, 2, 2));
        assertEquals(5, rule.score(1, 1, 1, 1, 1));
    }

    @Test
    @DisplayName("score() should return the value for a single die")
    void score_singleDie() {
        assertEquals(6, rule.score(6));
    }

    @Test
    @DisplayName("score() should return 0 for empty input")
    void score_emptyInput() {
        assertEquals(0, rule.score());
    }
}

