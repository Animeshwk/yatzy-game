package strategy.scoringrulestrategy.impl;

import strategy.scoringrulestrategy.DiceScoringRule;
import utils.YatzyUtils;


/**
 * {@link DiceScoringRule} implementation for the <em>Full House</em> category.
 * <p>
 * A <strong>full house</strong> is satisfied when the dice contain:
 * <ul>
 *   <li>one face appearing exactly <strong>three</strong> times (a "three-of-a-kind"), and</li>
 *   <li>a different face appearing exactly <strong>two</strong> times (a "pair").</li>
 * </ul>
 *
 * <h2>Scoring</h2>
 * <p>
 * If the dice form a full house, the score is the sum of all dice involved:
 * {@code (pairFace * 2) + (tripleFace * 3)}.
 * Otherwise, the score is {@code 0}.
 *
 * <h2>Examples</h2>
 * <pre>{@code
 * // 2,2 and 5,5,5 => 2*2 + 5*3 = 4 + 15 = 19
 * score(2, 2, 5, 5, 5) == 19
 *
 * // Not a full house (only one pair)
 * score(2, 2, 3, 4, 6) == 0
 *
 * // Not a full house (four-of-a-kind + single)
 * score(4, 4, 4, 4, 2) == 0
 * }</pre>
 *
 * <h2>Implementation notes</h2>
 * <ul>
 *   <li>Uses {@link YatzyUtils#counts(int...)} to compute face frequencies.</li>
 *   <li>Assumes standard dice faces {@code 1..6}.</li>
 *   <li>No explicit validation is performed for dice count (e.g., exactly 5) or value range (1..6);
 *       validation, if required, should be enforced by the caller or a higher-level component.</li>
 * </ul>
 */

public class FullHouseScoringRule implements DiceScoringRule {

    /**
     * No-args Constructor
     */
    public FullHouseScoringRule() {}

    /**
     * Computes full house score by identifying exactly one pair and exactly one three-of-a-kind.
     * <p>
     * This method scans face counts (typically faces 1..6) to locate:
     * <ul>
     *   <li>{@code pairFace}: a face with count {@code 2}</li>
     *   <li>{@code tripleFace}: a face with count {@code 3}</li>
     * </ul>
     * If both are found (and therefore are different faces), the score is computed as:
     * {@code pairFace * 2 + tripleFace * 3}; otherwise {@code 0}.
     *
     * @param dice the dice values to evaluate
     * @return the computed full house score, or {@code 0} if the dice do not form a full house
     */

    @Override
    public int score(int... dice) {
        return fullHouse(dice);
    }

    private int fullHouse(int... dice) {
        int[] counts = YatzyUtils.counts(dice);

        int pairFace = 0;
        int tripleFace = 0;

        for (int face = 1; face <= 6; face++) {
            int count = counts[face - 1];

            if (count == 2) {
                pairFace = face;
            }

            if (count == 3 && pairFace != face) {
                tripleFace = face;
            }
        }

        return (pairFace != 0 && tripleFace != 0)
                ? pairFace * 2 + tripleFace * 3
                : 0;
    }
}
