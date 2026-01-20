package strategy.outputstrategy.impl;

import strategy.outputstrategy.OutputStrategy;

public final class OutputStrategies {
    private OutputStrategies() {};

    public static OutputStrategy consoleOutputStrategy() {
        return System.out::println;
    }
}
