package hu.nye.progtech.sudoku.service.command.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import hu.nye.progtech.sudoku.model.GameState;
import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.service.command.performer.PutPerformer;
import hu.nye.progtech.sudoku.service.exception.MapValidationException;
import hu.nye.progtech.sudoku.service.exception.GameException;
import hu.nye.progtech.sudoku.service.validator.MapValidator;
import hu.nye.progtech.sudoku.ui.MapPrinter;
import hu.nye.progtech.sudoku.ui.PrintWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link PutCommand}.
 */
@ExtendWith(MockitoExtension.class)
public class PutCommandTest {

    private static final String PUT_COMMAND = "put 0 0 1";
    private static final String NOT_PUT_COMMAND = "not-put";

    private static final int ROW_INDEX = 0;
    private static final int COLUMN_INDEX = 0;
    private static final int NUMBER = 1;

    private static final MapVO MAP = new MapVO(0, 0, null, null);
    private static final MapVO NEW_MAP = new MapVO(0, 0, null, null);

    private static final String PUT_ERROR_MESSAGE = "Can't write to a fixed position";
    private static final String MAP_VALIDATION_ERROR_MESSAGE = "Can't write the given number to that position";

    private GameState gameState;
    @Mock
    private PutPerformer putPerformer;
    @Mock
    private MapValidator mapValidator;
    @Mock
    private MapPrinter mapPrinter;
    private PutCommand underTest;

    @BeforeEach
    public void setUp() {
        gameState = new GameState(MAP, false);
        underTest = new PutCommand(gameState, putPerformer, mapValidator, mapPrinter);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenTheGivenInputIsValidPutCommand() {
        // given in setup

        // when
        boolean result = underTest.canProcess(PUT_COMMAND);

        // then
        assertTrue(result);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenTheGivenInputIsNotValidPutCommand() {
        // given in setup

        // when
        boolean result = underTest.canProcess(NOT_PUT_COMMAND);

        // then
        assertFalse(result);
    }

    @Test
    public void testProcessShouldPerformValidPutOperation() throws GameException, MapValidationException {
        // given
        given(putPerformer.perform(MAP, ROW_INDEX, COLUMN_INDEX, NUMBER)).willReturn(NEW_MAP);

        // when
        underTest.process(PUT_COMMAND);

        // then
        verify(putPerformer).perform(MAP, ROW_INDEX, COLUMN_INDEX, NUMBER);
        verify(mapValidator).validate(NEW_MAP);
        assertEquals(NEW_MAP, gameState.getCurrentMap());
        verify(mapPrinter).printMap(NEW_MAP);
    }

    @Test
    public void testProcessShouldNotUpdateGameStateWhenPutPerformingFails() throws GameException {
        // given
        doThrow(GameException.class).when(putPerformer).perform(MAP, ROW_INDEX, COLUMN_INDEX, NUMBER);

        // when
        assertThrows(GameException.class, () -> underTest.process(PUT_COMMAND));

        // then
        verify(putPerformer).perform(MAP, ROW_INDEX, COLUMN_INDEX, NUMBER);
        verifyNoInteractions(mapValidator);
        assertEquals(MAP, gameState.getCurrentMap());
        verifyNoInteractions(mapPrinter);
    }

    @Test
    public void testProcessShouldNotUpdateGameStateWhenNewMapValidationFails() throws GameException, MapValidationException {
        // given
        given(putPerformer.perform(MAP, ROW_INDEX, COLUMN_INDEX, NUMBER)).willReturn(NEW_MAP);
        doThrow(MapValidationException.class).when(mapValidator).validate(NEW_MAP);

        // when
        assertThrows(MapValidationException.class, () -> underTest.process(PUT_COMMAND));

        // then
        verify(putPerformer).perform(MAP, ROW_INDEX, COLUMN_INDEX, NUMBER);
        verify(mapValidator).validate(NEW_MAP);
        assertEquals(MAP, gameState.getCurrentMap());
        verifyNoInteractions(mapPrinter);
    }

}
