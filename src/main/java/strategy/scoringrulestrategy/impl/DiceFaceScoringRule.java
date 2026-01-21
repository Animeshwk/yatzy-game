package strategy.scoringrulestrategy.impl;

import strategy.scoringrulestrategy.DiceScoringRule;

/**
 * {@link DiceScoringRule} implementation that scores dice by summing occurrences of a specific face value.
 * <p>
 * This rule is commonly used for "Ones", "Twos", "Threes", etc. categories in Yatzy-like games:
 * it awards {@code face} points for each die that exactly matches the configured {@code face}.
 *
 * <h2>Examples</h2>
 * <pre>{@code
 * DiceScoringRule ones = new DiceFaceScoringRule(1);
 * ones.score(1, 2, 1, 4, 6) == 2   // two 1s => 1 + 1
 *
 * DiceScoringRule sixes = new DiceFaceScoringRule(6);
 * sixes.score(6, 6, 2, 3, 1) == 12 // two 6s => 6 + 6
 * }</pre>
 *
 * <h2>Behavior</h2>
 * <ul>
 *   <li>Counts how many dice equal {@code face} and returns {@code face * count}.</li>
 *   <li>If no dice are provided (empty varargs), returns {@code 0}.</li>
 *   <li>If {@code dice} is {@code null}, the enhanced for-loop will throw a {@link NullPointerException}.</li>
 * </ul>
 */
public class DiceFaceScoringRule implements DiceScoringRule {
    private final int face;

    /**
     * Parameterized Constructor.
     * @param face the face value to count and sum (commonly 1..6 in standard dice games)
     */
    public DiceFaceScoringRule(int face) {
        this.face = face;
    }


    /**
     * Computes the score by summing {@code face} for each die that equals {@code face}.
     * <p>
     * A score of {@code 0} indicates that none of the dice matched the configured face
     * (or that no dice were provided).
     *
     * @param dice the dice values to evaluate
     * @return the total score contributed by dice equal to {@code face}
     */

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
