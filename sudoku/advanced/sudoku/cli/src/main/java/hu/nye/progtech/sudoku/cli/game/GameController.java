package hu.nye.progtech.sudoku.cli.game;

import hu.nye.progtech.sudoku.core.model.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Component that controls the flow of a game.
 */
public class GameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    private final GameStateHolder gameStateHolder;
    private final GameStepPerformer gameStepPerformer;

    public GameController(GameStateHolder gameStateHolder, GameStepPerformer gameStepPerformer) {
        this.gameStateHolder = gameStateHolder;
        this.gameStepPerformer = gameStepPerformer;
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
        return !gameStateHolder.getGameState().isShouldExit();
    }

}
