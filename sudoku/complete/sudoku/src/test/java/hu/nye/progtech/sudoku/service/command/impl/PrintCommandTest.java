package hu.nye.progtech.sudoku.service.command.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import hu.nye.progtech.sudoku.model.GameState;
import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.ui.MapPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link PrintCommand}.
 */
@ExtendWith(MockitoExtension.class)
public class PrintCommandTest {

    private static final String PRINT_COMMAND = "print";
    private static final String NOT_PRINT_COMMAND = "not-print";

    private static final MapVO MAP_VO = new MapVO(0, 0, null, null);

    private GameState gameState;
    @Mock
    private MapPrinter mapPrinter;

    private PrintCommand underTest;

    @BeforeEach
    public void setUp() {
        gameState = new GameState(MAP_VO, false);
        underTest = new PrintCommand(gameState, mapPrinter);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenTheGivenCommandIsPrint() {
        // given in setup

        // when
        boolean result = underTest.canProcess(PRINT_COMMAND);

        // then
        assertTrue(result);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenTheGivenCommandIsNotPrint() {
        // given in setup

        // when
        boolean result = underTest.canProcess(NOT_PRINT_COMMAND);

        // then
        assertFalse(result);
    }

    @Test
    public void testProcessShouldPrintTheCurrentMapFromGameState() {
        // given in setup

        // when
        underTest.process(PRINT_COMMAND);

        // then
        verify(mapPrinter).printMap(MAP_VO);
    }

}
