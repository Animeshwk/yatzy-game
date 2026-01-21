package strategy.scoringrulestrategy;


/**
 * Strategy interface for scoring a set of dice according to a specific rule.
 * <p>
 * Implementations encapsulate the scoring logic for a particular Yatzy category
 * (e.g., Chance, Yatzy, Full House, Three-of-a-kind, etc.).</p>
 * <p>This enables swapping scoring behaviors without changing the caller.</p>
 */

public interface DiceScoringRule {
    int score(int... dice);
}
