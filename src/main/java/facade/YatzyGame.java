package facade;

import builder.Roll;
import constant.Category;
import constant.RuleRegistry;
import strategy.outputstrategy.OutputStrategy;
import strategy.outputstrategy.impl.OutputStrategies;

public class YatzyGame {

    public YatzyGame() {}

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
