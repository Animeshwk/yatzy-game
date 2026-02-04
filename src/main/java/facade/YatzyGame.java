package facade;

import builder.Roll;
import constant.Category;
import constant.RuleRegistry;
import strategy.outputstrategy.OutputStrategy;
import strategy.outputstrategy.impl.OutputStrategies;


/**
 * Facade entry-point for playing/scoring a single Yatzy roll.
 * <p>
 * This class orchestrates the scoring workflow by:
 * <ol>
 *   <li>Resolving the scoring rule for the selected {@link Category} using {@link RuleRegistry}</li>
 *   <li>Computing the score for the given dice</li>
 *   <li>Building a {@link Roll} result payload</li>
 *   <li>Printing the result using an {@link OutputStrategy} (console output by default)</li>
 * </ol>
 */

public class YatzyGame {

    /**
     * No arg Constructor.
     */
    public YatzyGame() {}


    /**
     * Scores the provided dice against the chosen category and prints the result.
     * <p>
     * Internally, this method:
     * <ol>
     *   <li>Creates a new {@link RuleRegistry}</li>
     *   <li>Retrieves the scoring rule for {@code category}</li>
     *   <li>Calculates the score for {@code dice}</li>
     *   <li>Builds a {@link Roll} containing user-facing messages and the numeric score</li>
     *   <li>Prints the roll using the default console {@link OutputStrategy}</li>
     *  <li>Writes output to the console via {@link OutputStrategy#print(Roll)}.</li>
     * </ol>
     *
     * @param dice an array representing the rolled dice values (e.g., {@code [1, 3, 3, 5, 6]}).
     * @param category the score category the player has chosen for this roll.
     */

    public void play(int[] dice, Category category) {

        RuleRegistry registry = new RuleRegistry();
        int chanceScore = registry.ruleFor(category).score(dice);

        Roll roll = Roll.builder()
                .chosenCategory(String.format("You've chosen %s as score category", category))
                .score(chanceScore)
                .categoryResult(String.format("You've got %s", (chanceScore != 0) ? category : "NOTHING"))
                .build();

        OutputStrategy outputStrategy = OutputStrategies.consoleOutputStrategy();
        outputStrategy.print(roll);
    }
}
