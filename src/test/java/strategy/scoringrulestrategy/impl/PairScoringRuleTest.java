package strategy.scoringrulestrategy.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.scoringrulestrategy.DiceScoringRule;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PairScoringRuleTest {

    // ------------ PAIR -----------
    @Test
    @DisplayName("pairValue=1 behaves like 'pair': returns highest pair (face*2)")
    void score_pairValue1_returnsHighestPair() {
        DiceScoringRule rule = new PairScoringRule(1);

        assertEquals(12, rule.score(3, 3, 6, 6, 2));
    }

    @Test
    @DisplayName("pairValue=1 returns 0 when no pair exists")
    void score_pairValue1_returns0_whenNoPair() {
        DiceScoringRule rule = new PairScoringRule(1);

        assertEquals(0, rule.score(1, 2, 3, 4, 6));
    }

    // ------------ TWO_PAIR -----------
    @Test
    @DisplayName("pairValue=2 triggers twoPair logic: returns sum of two highest pairs")
    void score_pairValue2_twoPair_returnsSumOfTwoHighestPairs() {
        DiceScoringRule rule = new PairScoringRule(2);

        // pairs: 5 and 3 => 5*2 + 3*2 = 10 + 6 = 16
        assertEquals(16, rule.score(3, 3, 5, 5, 6));
    }

    @Test
    @DisplayName("pairValue=2 returns 0 when fewer than two pairs exist")
    void score_pairValue2_twoPair_returns0_whenOnlyOnePair() {
        DiceScoringRule rule = new PairScoringRule(2);

        // only one pair of 3s
        assertEquals(0, rule.score(3, 3, 2, 4, 6));
    }

    @Test
    @DisplayName("pairValue=2 counts >=2 occurrences as a pair (triple contributes one pair), matching current logic")
    void score_pairValue2_twoPair_allowsTripleAsPair() {
        DiceScoringRule rule = new PairScoringRule(2);

        // triple 3s counts as ">=2" for a pair, plus pair 5s => two pairs exist:
        // 5*2 + 3*2 = 10 + 6 = 16
        assertEquals(16, rule.score(3, 3, 3, 5, 5));
    }

    // ------------ THREE_OF_A_KIND -----------
    @Test
    @DisplayName("pairValue=3 returns highest three-of-a-kind (face*3)")
    void score_threeOfAKind_returnsHighestTriple() {
        DiceScoringRule rule = new PairScoringRule(3);

        // triple 2 => 2*3 = 6
        assertEquals(6, rule.score(2, 2, 2, 5, 6));
    }

    @Test
    @DisplayName("pairValue=3 returns 0 when no face occurs >= 3 times")
    void score_threeOfAKind_returns0_whenNotFound() {
        DiceScoringRule rule = new PairScoringRule(3);

        assertEquals(0, rule.score(2, 2, 4, 4, 6));
    }

    @Test
    @DisplayName("pairValue=3 prefers the highest face when multiple candidates exist")
    void score_threeOfAKind_prefersHighestFace() {
        DiceScoringRule rule = new PairScoringRule(3);

        assertEquals(12, rule.score(4, 4, 4, 2, 2));
    }

    // ------------ FOUR_OF_A_KIND -----------
    @Test
    @DisplayName("pairValue=4 returns highest four-of-a-kind (face*4)")
    void score_fourOfAKind_returnsHighestQuad() {
        DiceScoringRule rule = new PairScoringRule(4);

        assertEquals(24, rule.score(6, 6, 6, 6, 1));
    }

    // Tests for counts method

    @Test
    @DisplayName("twoPair(counts) returns sum of two highest pairs when two pairs exist")
    void twoPair_static_returnsSumOfTwoHighestPairs() {
        int[] counts = countsFromDice(3, 3, 5, 5, 6);

        assertEquals(16, PairScoringRule.twoPair(counts));
    }

    @Test
    @DisplayName("twoPair(counts) returns 0 when fewer than two pairs exist")
    void twoPair_static_returns0_whenOnlyOnePair() {
        int[] counts = countsFromDice(3, 3, 2, 4, 6);

        assertEquals(0, PairScoringRule.twoPair(counts));
    }

    @Test
    @DisplayName("twoPair(counts) uses >=2 rule: triple contributes as a pair (current behavior)")
    void twoPair_static_tripleCountsAsPair() {
        int[] counts = countsFromDice(3, 3, 3, 5, 5);

        assertEquals(16, PairScoringRule.twoPair(counts));
    }

    // Local helper for tests
    private static int[] countsFromDice(int... dice) {
        int[] c = new int[6];
        for (int d : dice) {
            c[d - 1]++;
        }
        return c;
    }
}

