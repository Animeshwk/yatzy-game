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
 */

public class FullHouseScoringRule implements DiceScoringRule {

    /**
     * No-args Constructor
     */
    public FullHouseScoringRule() {}

    /**
     * Computes full house score by identifying exactly one pair and exactly one three-of-a-kind.
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
