package facade;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import builder.Roll;
import constant.Category;
import constant.RuleRegistry;
import strategy.outputstrategy.impl.OutputStrategies;
import strategy.scoringrulestrategy.DiceScoringRule;
import strategy.outputstrategy.OutputStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;

class YatzyGameTest {

    @Test
    @DisplayName("when score != 0, prints roll with category in result")
    void play_whenScoreNonZero_printsRollWithCategory() {
        // Given
        int[] dice = {1, 2, 3, 4, 5};
        Category category = Category.CHANCE;

        DiceScoringRule ruleMock = mock(DiceScoringRule.class);
        OutputStrategy outputMock = mock(OutputStrategy.class);

        try (MockedStatic<OutputStrategies> outputStrategiesMock = mockStatic(OutputStrategies.class);

             MockedConstruction<RuleRegistry> registryConstruction = mockConstruction(RuleRegistry.class,
                             (registryMock, context) -> when(registryMock.ruleFor(category)).thenReturn(ruleMock))) {

            outputStrategiesMock.when(OutputStrategies::consoleOutputStrategy)
                    .thenReturn(outputMock);

            // when-then
            when(ruleMock.score(dice)).thenReturn(15);

            YatzyGame game = new YatzyGame();
            game.play(dice, category);

            // Assert to check if registry created once
            assertEquals(1, registryConstruction.constructed().size());
            RuleRegistry registryMock = registryConstruction.constructed().get(0);

            verify(registryMock).ruleFor(category);
            verify(ruleMock).score(dice);

            // Capture the Roll printed
            ArgumentCaptor<Roll> rollCaptor = ArgumentCaptor.forClass(Roll.class);
            verify(outputMock, times(1)).print(rollCaptor.capture());

            Roll printedRoll = rollCaptor.getValue();
            assertNotNull(printedRoll);

            // If Roll has getters (recommended)
            assertEquals("You've chosen CHANCE as score category", printedRoll.getChosenCategory());
            assertEquals(15, printedRoll.getScore());
            assertEquals("You've got CHANCE", printedRoll.getCategoryResult());
        }
    }

    @Test
    @DisplayName("play(): when score == 0, prints roll with NOTHING in result")
    void play_whenScoreZero_printsRollWithNothing() {
        // Given
        int[] dice = {2, 3, 4, 5, 6};
        Category category = Category.YATZY;

        DiceScoringRule ruleMock = mock(DiceScoringRule.class);
        OutputStrategy outputMock = mock(OutputStrategy.class);

        try (MockedStatic<OutputStrategies> outputStrategiesMock = mockStatic(OutputStrategies.class);
             MockedConstruction<RuleRegistry> registryConstruction = mockConstruction(RuleRegistry.class,
                     (registryMock, context) -> when(registryMock.ruleFor(category)).thenReturn(ruleMock))) {

            outputStrategiesMock.when(OutputStrategies::consoleOutputStrategy)
                    .thenReturn(outputMock);

            // when-then
            when(ruleMock.score(dice)).thenReturn(0);

            YatzyGame game = new YatzyGame();
            game.play(dice, category);

            // Assert
            assertEquals(1, registryConstruction.constructed().size());
            RuleRegistry registryMock = registryConstruction.constructed().get(0);

            verify(registryMock).ruleFor(category);
            verify(ruleMock).score(dice);

            ArgumentCaptor<Roll> rollCaptor = ArgumentCaptor.forClass(Roll.class);
            verify(outputMock, times(1)).print(rollCaptor.capture());

            Roll printedRoll = rollCaptor.getValue();
            assertNotNull(printedRoll);

            assertEquals("You've chosen YATZY as score category", printedRoll.getChosenCategory());
            assertEquals(0, printedRoll.getScore());
            assertEquals("You've got NOTHING", printedRoll.getCategoryResult());
        }
    }
}

