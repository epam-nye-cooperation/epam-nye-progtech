package hu.nye.progtech.sudoku.service.command.impl;

import hu.nye.progtech.sudoku.model.GameState;
import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.persistence.GameSavesRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

public class SaveCommandTest {

    private SaveCommand underTest;

    private GameSavesRepository gameSavesRepository;
    private GameState gameState;

    @BeforeEach
    public void init() {
        gameSavesRepository = Mockito.mock(GameSavesRepository.class);
        gameState = Mockito.mock(GameState.class);
        underTest = new SaveCommand(gameSavesRepository, gameState);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenTheInputIsSave() {
        // Given
        String input = "save";
        boolean expected = true;

        // When
        boolean actual = underTest.canProcess(input);

        // Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenTheInputIsNotSave() {
        // Given
        String input = "load";
        boolean expected = false;

        // When
        boolean actual = underTest.canProcess(input);

        // Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenTheInputIsNull() {
        // Given
        String input = null;
        boolean expected = false;

        // When
        boolean actual = underTest.canProcess(input);

        // Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testProcessShouldRetrieveCurrentMapAndPassItToGameSavesRepository() {
        // Given
        String input = "input";
        MapVO mapVO = Mockito.mock(MapVO.class);
        Mockito.when(gameState.getCurrentMap()).thenReturn(mapVO);

        // When
        underTest.process(input);

        // Then
        Mockito.verify(gameState).getCurrentMap();
        Mockito.verify(gameSavesRepository).save(mapVO);
        Mockito.verifyNoMoreInteractions(gameSavesRepository, gameState, mapVO);
    }

    @Test
    public void testProcessShouldRetrieveCurrentMapAndPassItToGameSavesRepositoryWhenInputIsNull() {
        // Given
        String input = null;
        MapVO mapVO = Mockito.mock(MapVO.class);
        Mockito.when(gameState.getCurrentMap()).thenReturn(mapVO);

        // When
        underTest.process(input);

        // Then
        Mockito.verify(gameState).getCurrentMap();
        Mockito.verify(gameSavesRepository).save(mapVO);
        Mockito.verifyNoMoreInteractions(gameSavesRepository, gameState, mapVO);
    }

}
