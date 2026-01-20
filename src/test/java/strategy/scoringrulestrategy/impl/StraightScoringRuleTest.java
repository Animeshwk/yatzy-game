package strategy.scoringrulestrategy.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.scoringrulestrategy.DiceScoringRule;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StraightScoringRuleTest {

    @Test
    @DisplayName("startFace=1: returns 15 for small straight (1..5) in any order")
    void score_smallStraight_valid_returns15() {
        DiceScoringRule rule = new StraightScoringRule(1);

        assertEquals(15, rule.score(1, 2, 3, 4, 5));
        assertEquals(15, rule.score(5, 4, 3, 2, 1));
        assertEquals(15, rule.score(2, 1, 4, 5, 3));
    }

    @Test
    @DisplayName("startFace=2: returns 20 for large straight (2..6) in any order")
    void score_largeStraight_valid_returns20() {
        DiceScoringRule rule = new StraightScoringRule(2);

        assertEquals(20, rule.score(2, 3, 4, 5, 6));
        assertEquals(20, rule.score(6, 5, 4, 3, 2));
        assertEquals(20, rule.score(4, 2, 6, 3, 5));
    }

    @Test
    @DisplayName("startFace=1: returns 0 when dice are not a small straight (duplicates/missing)")
    void score_smallStraight_invalid_returns0() {
        DiceScoringRule rule = new StraightScoringRule(1);

        // Duplicate
        assertEquals(0, rule.score(1, 2, 3, 4, 4));

        // Missing 5 (has 6 instead)
        assertEquals(0, rule.score(1, 2, 3, 4, 6));

        // All same
        assertEquals(0, rule.score(2, 2, 2, 2, 2));
    }

    @Test
    @DisplayName("startFace=2: returns 0 when dice are not a large straight (duplicates/missing)")
    void score_largeStraight_invalid_returns0() {
        DiceScoringRule rule = new StraightScoringRule(2);

        // Duplicate
        assertEquals(0, rule.score(2, 3, 4, 5, 5));

        // Missing 6 (has 1 instead)
        assertEquals(0, rule.score(1, 2, 3, 4, 5));

        // Random non-straight
        assertEquals(0, rule.score(2, 2, 3, 4, 6));
    }
}

