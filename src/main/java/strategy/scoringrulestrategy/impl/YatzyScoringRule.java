package strategy.scoringrulestrategy.impl;

import strategy.scoringrulestrategy.DiceScoringRule;


/**
 * {@link DiceScoringRule} implementation for the <em>Yatzy</em> category.
 * <p>
 * A Yatzy is achieved when <strong>all five dice show the same face value</strong>.
 * When the condition is satisfied, this rule awards a fixed score of {@code 50}.
 *
 * <h2>Behavior</h2>
 * <ul>
 *   <li>Returns {@code 50} if and only if:
 *     <ul>
 *       <li>{@code dice} is non-null,</li>
 *       <li>{@code dice.length == 5}, and</li>
 *       <li>all five values are identical.</li>
 *     </ul>
 *   </li>
 *   <li>Returns {@code 0} for any of the following:
 *     <ul>
 *       <li>{@code dice == null}</li>
 *       <li>{@code dice.length != 5}</li>
 *       <li>not all dice match the first die</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <h2>Examples</h2>
 * <pre>{@code
 * score(6, 6, 6, 6, 6) == 50
 * score(6, 6, 6, 6, 5) == 0
 * score(1, 1, 1, 1)    == 0   // not exactly 5 dice
 * score((int[]) null)  == 0
 * }</pre>
 */

public class YatzyScoringRule implements DiceScoringRule {

    /**
     * No-argument Constructor
     */
    public YatzyScoringRule() {}

    /**
     * Computes the Yatzy score for the given dice.
     * <p>
     * The score is {@code 50} only when the input represents exactly five dice and all values are equal.
     * Otherwise, this method returns {@code 0}.
     *
     * @param dice the dice values to evaluate; must contain exactly 5 elements to be considered
     * @return {@code 50} if the dice form a Yatzy; otherwise {@code 0}
     */

    @Override
    public int score(int... dice) {
        if (dice == null || dice.length != 5) {
            return 0;
        }

        int firstDie = dice[0];
        for (int i = 1; i < dice.length; i++) {
            if (dice[i] != firstDie) {
                return 0;
            }
        }
        return 50;
    }
}
