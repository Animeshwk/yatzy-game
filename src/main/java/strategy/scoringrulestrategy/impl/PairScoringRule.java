package strategy.scoringrulestrategy.impl;

import strategy.scoringrulestrategy.DiceScoringRule;
import utils.YatzyUtils;

import static utils.YatzyUtils.counts;


/**
 * {@link DiceScoringRule} implementation for scoring <em>Pair</em> categories based on face counts.
 * <p>
 * This rule is configurable via {@code pairValue} and supports multiple related categories:
 * <ul>
 *   <li><b>One Pair</b>: {@code pairValue == 1} → scores the highest face with at least 2 occurrences.</li>
 *   <li><b>Two Pair</b>: {@code pairValue == 2} → scores the two highest distinct pairs.</li>
 *   <li><b>N-of-a-kind</b>: {@code pairValue >= 3} → scores the highest face with at least {@code pairValue} occurrences.</li>
 * </ul>
 *
 * <h2>Scoring</h2>
 * <ul>
 *   <li><b>One Pair</b>: returns {@code highestFace * 2}, or {@code 0} if no pair exists.</li>
 *   <li><b>Two Pair</b>: returns {@code (highestPairFace * 2) + (secondHighestPairFace * 2)},
 *       or {@code 0} if fewer than two pairs exist.</li>
 *   <li><b>N-of-a-kind</b>: returns {@code highestFace * pairValue}, or {@code 0} if no such group exists.</li>
 * </ul>
 */

public class PairScoringRule implements DiceScoringRule {
    private final int pairValue;

    /**
     * Parameterized Constructor.
     * @param pairValue category selector / multiplicity: {@code 1} for one pair, {@code 2} for two pair, {@code >=3} for N-of-a-kind
     */
    public PairScoringRule(int pairValue) {
        this.pairValue = pairValue;
    }


/**
 * Computes the score for the configured "pair-like" category using the provided dice.
 * <p>
 * This method first converts the dice into face frequencies using {@code counts(dice)} and then
 * delegates to internal logic:
 * <ul>
 * <li>If {@code pairValue == 2}, computes two pair via {@link #twoPair(int[])}.</li>
 *   <li>Otherwise, computes the highest qualifying face for one-pair or N-of-a-kind.</li>
 * </ul>
 *
 * @param dice the dice values to evaluate
 * @return the computed score for the configured category, or {@code 0} if not satisfied
 */

    @Override
    public int score(int... dice) {
        int[] counts = counts(dice);

        return (pairValue == 2) ? twoPair(counts) : scoreOfAKind(pairValue, counts);
    }

    /**
     * Computes the score for One Pair / N-of-a-kind otherwise by selecting the highest face meeting the required multiplicity.
     * </ul>
     *
     * <p>
     * For {@code pairValue != 2}, the required multiplicity is determined as:
     * <ul>
     *   <li>{@code pairValue == 1} → required multiplicity {@code 2} (one pair)</li>
     *   <li>{@code pairValue >= 3} → required multiplicity {@code pairValue} (N-of-a-kind)</li>
     * </ul>
     *
     * @param pairValue category selector / multiplicity configuration
     * @param counts an array of face frequencies, where {@code counts[face - 1]} is the count for that face
     * @return the score for the configured category, or {@code 0} if no qualifying group exists
     */

    private int scoreOfAKind(int pairValue, int[] counts) {
        int pair = (pairValue == 1) ? 2 : pairValue;

        for (int face = 6; face >= 1; face--) {
            if (counts[face - 1] >= pair) {
                return face * pair; // highest pair
            }
        }
        return 0;
    }

    public int twoPair(int[] counts) {
        int pairsFound = 0;
        int total = 0;

        for (int face = 6; face >= 1; face--) {
            if (counts[face - 1] >= 2) {
                pairsFound++;
                total += face * 2;

                if (pairsFound == 2) {
                    return total;
                }
            }
        }
        return 0;
    }
}