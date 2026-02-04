package strategy.scoringrulestrategy.impl;

import strategy.scoringrulestrategy.DiceScoringRule;

import java.util.Arrays;

/**
 * {@link DiceScoringRule} implementation for the <em>Chance</em> category.
 * <p>
 * The Chance rule typically awards the sum of all dice values, regardless of
 * any particular pattern or combination. This makes it a simple and always-applicable
 * scoring option in many Yatzy-style games.
 */
public class ChanceScoringRule implements DiceScoringRule {

    /**
     * No-args Constructor.
     */
    public ChanceScoringRule() {}

    /**
     * Computes the Chance score for the given dice by summing all values.
     *
     * @param dice the dice values to sum
     * @return the sum of all dice values; {@code 0} if {@code dice} is empty
     * @throws NullPointerException if {@code dice} is {@code null}
     */

    @Override
    public int score(int... dice) {
        return Arrays.stream(dice).sum();
    }
}
