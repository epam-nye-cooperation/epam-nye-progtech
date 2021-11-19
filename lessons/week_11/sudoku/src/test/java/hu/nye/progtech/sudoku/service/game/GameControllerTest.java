package hu.nye.progtech.sudoku.service.game;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import hu.nye.progtech.sudoku.model.GameState;
import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.service.util.MapUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link GameController}.
 */
@ExtendWith(MockitoExtension.class)
public class GameControllerTest {

    private static final MapVO MAP_VO = new MapVO(0, 0, null, null);

    private GameState gameState;
    @Mock
    private GameStepPerformer gameStepPerformer;
    @Mock
    private MapUtil mapUtil;

    private GameController underTest;

    @Test
    public void testStartShouldLoopTheGameUntilTheUserDoesNotForceExit() {
        // given
        gameState = new GameState(null, true);
        underTest = new GameController(gameState, gameStepPerformer, mapUtil);

        // when
        underTest.start();

        // then
        verifyNoInteractions(gameStepPerformer);
    }

    @Test
    public void testStartShouldLoopTheGameUntilTheMapIsNotCompleted() {
        // given
        gameState = new GameState(MAP_VO, false);
        underTest = new GameController(gameState, gameStepPerformer, mapUtil);
        given(mapUtil.isMapCompleted(MAP_VO)).willReturn(false, true);

        // when
        underTest.start();

        // then
        verify(mapUtil, times(2)).isMapCompleted(MAP_VO);
        verify(gameStepPerformer, times(1)).performGameStep();
    }

}
