package hu.nye.progtech.sudoku.service.game;

import hu.nye.progtech.sudoku.model.GameState;
import hu.nye.progtech.sudoku.service.util.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Component that controls the flow of a game.
 */
public class GameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    private final GameState gameState;
    private final GameStepPerformer gameStepPerformer;
    private final MapUtil mapUtil;

    public GameController(GameState gameState, GameStepPerformer gameStepPerformer, MapUtil mapUtil) {
        this.gameState = gameState;
        this.gameStepPerformer = gameStepPerformer;
        this.mapUtil = mapUtil;
    }

    /**
     * Starts the game loop.
     */
    public void start() {
        LOGGER.info("Starting game loop");
        while (isGameInProgress()) {
            gameStepPerformer.performGameStep();
        }
        LOGGER.info("Game loop finished");
    }

    private boolean isGameInProgress() {
        return !gameState.isShouldExit() && !mapUtil.isMapCompleted(gameState.getCurrentMap());
    }

}
