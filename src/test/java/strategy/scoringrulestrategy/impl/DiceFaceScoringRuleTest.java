package strategy.scoringrulestrategy.impl;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.scoringrulestrategy.DiceScoringRule;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiceFaceScoringRuleTest {

    @Test
    @DisplayName("Returns sum of matching face values (e.g., face=1 counts ones)")
    void score_countsMatchingFaceValues() {
        DiceScoringRule onesRule = new DiceFaceScoringRule(1);

        // three 1s -> 1 + 1 + 1 = 3
        assertEquals(3, onesRule.score(1, 2, 1, 4, 1));
    }

    @Test
    @DisplayName("Returns 0 when the requested face does not appear")
    void score_returnsZeroWhenNoMatches() {
        DiceScoringRule sixesRule = new DiceFaceScoringRule(6);

        assertEquals(0, sixesRule.score(1, 2, 3, 4, 5));
    }

    @Test
    @DisplayName("Works with any number of dice")
    void score_varargs_anyLength() {
        DiceScoringRule twosRule = new DiceFaceScoringRule(2);
        assertEquals(8, twosRule.score(2, 2, 2, 2));
    }
}

