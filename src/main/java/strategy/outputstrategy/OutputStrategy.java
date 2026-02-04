package strategy.outputstrategy;

import builder.Roll;


/**
 * Strategy interface responsible for presenting or emitting a {@link Roll} result.
 * <p>
 * Implementations encapsulate *how* a {@code Roll} is output (e.g., console, log, file, UI,
 * remote endpoint), allowing the game/scoring flow to remain independent from presentation concerns.
 *
 * <h2>Typical implementations</h2>
 * <ul>
 *   <li>Console output (stdout)</li>
 *   <li>Logging (e.g., SLF4J/Log4J)</li>
 *   <li>GUI rendering</li>
 * </ul>
 */

public interface OutputStrategy {
    void print(Roll roll);
}
