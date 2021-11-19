package hu.nye.progtech.sudoku.service.command.impl;

import hu.nye.progtech.sudoku.model.GameState;
import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.persistence.GameSavesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LoadCommandTest {

    private LoadCommand underTest;

    private GameSavesRepository gameSavesRepository;
    private GameState gameState;

    @BeforeEach
    public void init() {
        gameSavesRepository = Mockito.mock(GameSavesRepository.class);
        gameState = Mockito.mock(GameState.class);
        underTest = new LoadCommand(gameSavesRepository, gameState);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenInputIsLoad() {
        // Given
        String input = "load";
        boolean expected = true;

        // When
        boolean actual = underTest.canProcess(input);

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verifyNoMoreInteractions(gameSavesRepository, gameState);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenInputIsNotLoad() {
        // Given
        String input = "save";
        boolean expected = false;

        // When
        boolean actual = underTest.canProcess(input);

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verifyNoMoreInteractions(gameSavesRepository, gameState);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenInputIsNull() {
        // Given
        String input = null;
        boolean expected = false;

        // When
        boolean actual = underTest.canProcess(input);

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verifyNoMoreInteractions(gameSavesRepository, gameState);
    }

    @Test
    public void testProcessShouldRetrieveMapVOFromRepositoryAndSetTheCurrentMapInGameState() {
        // Given
        String input = null;
        MapVO mapVO = Mockito.mock(MapVO.class);
        Mockito.when(gameSavesRepository.load()).thenReturn(mapVO);

        // When
        underTest.process(input);

        // Then
        Mockito.verify(gameSavesRepository).load();
        Mockito.verify(gameState).setCurrentMap(mapVO);
        Mockito.verifyNoMoreInteractions(gameSavesRepository, gameState, mapVO);
    }

}
