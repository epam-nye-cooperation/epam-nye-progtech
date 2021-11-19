package hu.nye.progtech.sudoku.service.command.impl;

import hu.nye.progtech.sudoku.service.command.Command;
import hu.nye.progtech.sudoku.ui.PrintWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A default command, which should be run when no other {@link Command}
 * implementations were able to process the input.
 */
public class DefaultCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCommand.class);

    private static final String UNKNOWN_COMMAND_MESSAGE = "Unknown command";

    private final PrintWrapper printWrapper;

    public DefaultCommand(PrintWrapper printWrapper) {
        this.printWrapper = printWrapper;
    }

    @Override
    public boolean canProcess(String input) {
        return true;
    }

    @Override
    public void process(String input) {
        LOGGER.info("Performing default command");
        printWrapper.printLine(UNKNOWN_COMMAND_MESSAGE);
    }

}
