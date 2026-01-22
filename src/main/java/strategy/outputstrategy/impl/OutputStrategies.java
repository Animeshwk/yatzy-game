package strategy.outputstrategy.impl;

import builder.Roll;
import strategy.outputstrategy.OutputStrategy;


/**
 * Factory/registry for {@link OutputStrategy} implementations.
 * <p>
 * This is a non-instantiable utility class that centralizes construction of commonly used
 * {@link OutputStrategy} instances (e.g., console output, logging output, file output, etc.), keeping strategy creation in one place.
 *
 * <h2>Extensibility</h2>
 * Add new factory methods for additional strategies, for example:
 * <ul>
 *   <li>{@code fileOutputStrategy(Path path)}</li>
 *   <li>{@code loggerOutputStrategy(Logger logger)}</li>
 *   <li>{@code inMemoryOutputStrategy(List<Roll> sink)}</li>
 * </ul>
 */

public final class OutputStrategies {

    /**
     * No-argument Constructor
     */
    private OutputStrategies() {}


    /**
     * Creates an {@link OutputStrategy} that prints the given {@link Roll} to the standard output stream.
     * <p>
     * The returned strategy is backed by {@link System#out} and uses {@code println(...)}. As a result:
     * <ul>
     *   <li>The {@link Roll} is rendered using its {@link Object#toString()} implementation.</li>
     *   <li>Output is a side effect (console I/O) and may be redirected depending on the runtime environment.</li>
     * </ul>
     *
     * @return an {@link OutputStrategy} that prints {@link Roll} instances to the console
     */

    public static OutputStrategy consoleOutputStrategy() {
        return System.out::println;
    }
}
