package constant;


import strategy.scoringrulestrategy.DiceScoringRule;
import strategy.scoringrulestrategy.impl.*;

import java.util.HashMap;
import java.util.Map;

public final class RuleRegistry {
    private final Map<Category, DiceScoringRule> rules = new HashMap<>();

    public RuleRegistry() {
        rules.put(Category.CHANCE, new ChanceScoringRule());
        rules.put(Category.YATZY, new YatzyScoringRule());

        rules.put(Category.ONES,   new DiceFaceScoringRule(1));
        rules.put(Category.TWOS,   new DiceFaceScoringRule(2));
        rules.put(Category.THREES, new DiceFaceScoringRule(3));
        rules.put(Category.FOURS,  new DiceFaceScoringRule(4));
        rules.put(Category.FIVES,  new DiceFaceScoringRule(5));
        rules.put(Category.SIXES,  new DiceFaceScoringRule(6));

        rules.put(Category.PAIR, new PairScoringRule(1));
        rules.put(Category.TWO_PAIR, new PairScoringRule(2));
        rules.put(Category.THREE_OF_A_KIND, new PairScoringRule(3));
        rules.put(Category.FOUR_OF_A_KIND, new PairScoringRule(4));

        rules.put(Category.SMALL_STRAIGHT, new StraightScoringRule(1));
        rules.put(Category.LARGE_STRAIGHT, new StraightScoringRule(2));

        rules.put(Category.FULL_HOUSE, new FullHouseScoringRule());
    }

    public DiceScoringRule ruleFor(Category category) {
        DiceScoringRule rule = rules.get(category);

        if (rule == null) {
            throw new IllegalArgumentException("No rule registered for: " + category);
        }

        return rule;
    }
}

