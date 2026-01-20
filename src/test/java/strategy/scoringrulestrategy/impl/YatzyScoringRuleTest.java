package strategy.scoringrulestrategy.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.scoringrulestrategy.DiceScoringRule;

import static org.junit.jupiter.api.Assertions.assertEquals;

class YatzyScoringRuleTest {

    private final DiceScoringRule rule = new YatzyScoringRule();

    @Test
    @DisplayName("Returns 50 when exactly 5 dice are all the same (valid yatzy)")
    void score_returns50_whenAllFiveDiceAreSame() {
        assertEquals(50, rule.score(1, 1, 1, 1, 1));
        assertEquals(50, rule.score(6, 6, 6, 6, 6));
        assertEquals(50, rule.score(3, 3, 3, 3, 3));
    }

    @Test
    @DisplayName("Returns 0 when dice are not all the same")
    void score_returns0_whenNotAllDiceMatch() {
        assertEquals(0, rule.score(6, 6, 6, 6, 5));
        assertEquals(0, rule.score(1, 1, 1, 2, 1));
        assertEquals(0, rule.score(2, 3, 2, 2, 2));
    }
}

