package strategy.scoringrulestrategy.impl;

import strategy.scoringrulestrategy.DiceScoringRule;
import utils.YatzyUtils;


/**
 * {@link DiceScoringRule} implementation for scoring <em>Straights</em> in a Yatzy-style game.
 * <p>
 * This rule supports two straight variants based on the configured {@code startFace}:
 * <ul>
 *   <li><b>Small straight</b> ({@code startFace == 1}): checks for faces {@code 1,2,3,4,5}
 *       and scores {@code 15}.</li>
 *   <li><b>Large straight</b> ({@code startFace == 2}): checks for faces {@code 2,3,4,5,6}
 *       and scores {@code 20}.</li>
 * </ul>
 *
 * <h2>Behavior</h2>
 * <ul>
 *   <li>If {@code startFace == 1}, the rule evaluates a small straight.</li>
 *   <li>If {@code startFace == 2}, the rule evaluates a large straight.</li>
 *   <li>For any other {@code startFace} value, the score is {@code 0}.</li>
 *   <li>The straight condition requires each expected face to occur <b>exactly once</b>.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>{@code
 * DiceScoringRule small = new StraightScoringRule(1);
 * small.score(1, 2, 3, 4, 5) == 15
 * small.score(1, 2, 3, 4, 6) == 0
 *
 * DiceScoringRule large = new StraightScoringRule(2);
 * large.score(2, 3, 4, 5, 6) == 20
 * large.score(1, 3, 4, 5, 6) == 0
 * }</pre>
 */

public class StraightScoringRule implements DiceScoringRule {

    private final int startFace;

    /**
     * Parameterized Constructor
     * @param startFace the starting face of the straight; {@code 1} for small straight, {@code 2} for large straight
     */
    public StraightScoringRule(int startFace) {
        this.startFace = startFace;
    }


    /**
     * Computes the straight score for the given dice based on the configured {@code startFace}.
     * <p>
     * This method delegates to:
     * <ul>
     *   <li>{@link #smallStraight(int...)} when {@code startFace == 1}</li>
     *   <li>{@link #largeStraight(int...)} when {@code startFace == 2}</li>
     * </ul>
     * For any other {@code startFace}, the method returns {@code 0}.
     *
     * @param dice the dice values to evaluate
     * @return {@code 15} for a valid small straight, {@code 20} for a valid large straight, otherwise {@code 0}
     */

    @Override
    public int score(int... dice) {

        // startFace=1 => checks 1..5
        if(startFace == 1) {
            return smallStraight(dice);
        }

        // startFace=2 => checks 2..6
        if(startFace == 2) {
            return largeStraight(dice);
        }
        return 0;
    }


    /**
     * Evaluates whether the dice form a small straight ({@code 1..5}).
     *
     * @param dice the dice values to evaluate
     * @return {@code 15} if the dice contain exactly one each of faces {@code 1..5}; otherwise {@code 0}
     */
    private int smallStraight(int... dice) {
        int[] counts = YatzyUtils.counts(dice);

        // startFace=1 => checks 1..5
        return isStraight(startFace, counts) ? 15 : 0;
    }

    /**
     * Evaluates whether the dice form a large straight ({@code 2..6}).
     *
     * @param dice the dice values to evaluate
     * @return {@code 20} if the dice contain exactly one each of faces {@code 2..6}; otherwise {@code 0}
     */

    private int largeStraight(int... dice) {
        int[] counts = YatzyUtils.counts(dice);

        // startFace=2 => checks 2..6
        return isStraight(startFace, counts) ? 20 : 0;
    }


    /**
     * Checks whether the face-frequency array represents a straight of five consecutive faces
     * beginning at {@code startFace}.
     * <p>
     * A straight is satisfied only if each expected face occurs <b>exactly once</b>
     * (i.e., {@code counts[face - 1] == 1} for each face in the required range).
     *
     * @param startFace the first face of the required straight
     * @param counts face frequencies, where {@code counts[face - 1]} is the count for that face
     * @return {@code true} if the counts contain exactly one of each required face; otherwise {@code false}
     */

    private static boolean isStraight(int startFace, int[] counts) {
        for (int face = startFace; face < startFace + 5; face++) {
            if (counts[face - 1] != 1) {
                return false;
            }
        }
        return true;
    }
}
