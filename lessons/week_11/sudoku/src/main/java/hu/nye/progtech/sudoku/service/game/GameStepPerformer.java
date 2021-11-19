package hu.nye.progtech.sudoku.service.game;

import hu.nye.progtech.sudoku.service.command.InputHandler;
import hu.nye.progtech.sudoku.service.input.UserInputReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Component that performs a game step.
 */
public class GameStepPerformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameStepPerformer.class);

    private final UserInputReader userInputReader;
    private final InputHandler inputHandler;

    public GameStepPerformer(UserInputReader userInputReader, InputHandler inputHandler) {
        this.userInputReader = userInputReader;
        this.inputHandler = inputHandler;
    }

    /**
     * Performs a game step.
     *
     * A game step consists of taking the input from the user, then handling
     * the input.
     */
    public void performGameStep() {
        String input = userInputReader.readInput();
        LOGGER.info("Read user input = '{}'", input);
        inputHandler.handleInput(input);
    }

}
