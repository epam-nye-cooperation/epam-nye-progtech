package hu.nye.progtech.sudoku.cli.command.impl;

import hu.nye.progtech.sudoku.cli.command.Command;
import hu.nye.progtech.sudoku.cli.game.GameStateHolder;
import hu.nye.progtech.sudoku.core.model.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command used to exit from the game.
 */
public class ExitCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExitCommand.class);

    private static final String EXIT_COMMAND = "exit";

    private final GameStateHolder gameStateHolder;

    public ExitCommand(GameStateHolder gameStateHolder) {
        this.gameStateHolder = gameStateHolder;
    }

    @Override
    public boolean canProcess(String input) {
        return EXIT_COMMAND.equals(input);
    }

    @Override
    public void process(String input) {
        LOGGER.info("Performing exit command");
        gameStateHolder.getGameState().setShouldExit(true);
    }

}
