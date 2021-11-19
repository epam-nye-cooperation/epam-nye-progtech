package hu.nye.progtech.sudoku.service.command;

import java.util.List;

/**
 * Component that handles a given input.
 */
public class InputHandler {

    private final List<Command> commandList;

    public InputHandler(List<Command> commandList) {
        this.commandList = commandList;
    }

    /**
     * Handles an input through a list of {@link Command}s.
     *
     * Only the first applicable command will be run.
     *
     * @param input the input as a string to be handled
     */
    public void handleInput(String input) {
        for (Command command : commandList) {
            if (command.canProcess(input)) {
                command.process(input);
                break;
            }
        }
    }

}
