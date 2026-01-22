package strategy.scoringrulestrategy.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.scoringrulestrategy.DiceScoringRule;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FullHouseScoringRuleTest {

    private final DiceScoringRule rule = new FullHouseScoringRule();

    @Test
    @DisplayName("Returns correct score for a full house (pair + three-of-a-kind)")
    void score_returnsCorrectValue_forFullHouse() {
        // 2,2 and 3,3,3 => 2*2 + 3*3 = 4 + 9 = 13
        assertEquals(13, rule.score(2, 2, 3, 3, 3));

        // 6,6 and 1,1,1 => 6*2 + 1*3 = 12 + 3 = 15
        assertEquals(15, rule.score(6, 6, 1, 1, 1));

        // 3,3 and 2,2,2 => 3*2 + 2*3 = 6 + 6 = 12
        assertEquals(12, rule.score(2, 2, 2, 3, 3));
    }

    @Test
    @DisplayName("Returns 0 when not a full house")
    void score_returns0_whenNotFullHouse() {
        // Two pairs (no triple)
        assertEquals(0, rule.score(2, 2, 3, 3, 4));

        // Three-of-a-kind only (no pair)
        assertEquals(0, rule.score(4, 4, 4, 2, 6));

        // Three-of-a-kind only (no pair)
        assertEquals(0, rule.score(4, 4, 4, 4, 4));
    }
}

