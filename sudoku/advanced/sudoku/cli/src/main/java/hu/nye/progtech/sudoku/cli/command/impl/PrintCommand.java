package hu.nye.progtech.sudoku.cli.command.impl;

import hu.nye.progtech.sudoku.cli.command.Command;
import hu.nye.progtech.sudoku.cli.game.GameStateHolder;
import hu.nye.progtech.sudoku.core.model.GameState;
import hu.nye.progtech.sudoku.cli.printer.MapPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command used to request the printing of the current state
 * of the game map.
 */
public class PrintCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintCommand.class);

    private static final String PRINT_COMMAND = "print";

    private final GameStateHolder gameStateHolder;
    private final MapPrinter mapPrinter;

    public PrintCommand(GameStateHolder gameStateHolder, MapPrinter mapPrinter) {
        this.gameStateHolder = gameStateHolder;
        this.mapPrinter = mapPrinter;
    }

    @Override
    public boolean canProcess(String input) {
        return PRINT_COMMAND.equals(input);
    }

    @Override
    public void process(String input) {
        LOGGER.info("Performing print command");
        mapPrinter.printMap(gameStateHolder.getGameState().getCurrentMap());
    }

}
