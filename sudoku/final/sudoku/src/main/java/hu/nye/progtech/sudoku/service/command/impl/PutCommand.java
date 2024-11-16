package hu.nye.progtech.sudoku.service.command.impl;

import java.util.regex.Pattern;

import hu.nye.progtech.sudoku.model.GameState;
import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.service.command.Command;
import hu.nye.progtech.sudoku.service.command.performer.PutPerformer;
import hu.nye.progtech.sudoku.service.exception.MapValidationException;
import hu.nye.progtech.sudoku.service.exception.GameException;
import hu.nye.progtech.sudoku.service.validator.MapValidator;
import hu.nye.progtech.sudoku.ui.MapPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command used to write a number to a given field of the map.
 */
public class PutCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(PutCommand.class);

    private static final String PUT_COMMAND_REGEX = "^put [0-8] [0-8] [1-9]$";

    private final GameState gameState;
    private final PutPerformer putPerformer;
    private final MapValidator mapValidator;
    private final MapPrinter mapPrinter;

    public PutCommand(GameState gameState, PutPerformer putPerformer, MapValidator mapValidator,
                      MapPrinter mapPrinter) {
        this.gameState = gameState;
        this.putPerformer = putPerformer;
        this.mapValidator = mapValidator;
        this.mapPrinter = mapPrinter;
    }

    @Override
    public boolean canProcess(String input) {
        return Pattern.matches(PUT_COMMAND_REGEX, input);
    }

    @Override
    public void process(String input) {
        String[] parts = input.split(" ");
        int rowIndex = Integer.parseInt(parts[1]);
        int columnIndex = Integer.parseInt(parts[2]);
        int number = Integer.parseInt(parts[3]);

        LOGGER.info("Performing put command with rowIndex = {}, columnIndex = {}, number = {}", rowIndex, columnIndex, number);

        MapVO newMap = putPerformer.perform(gameState.getCurrentMap(), rowIndex, columnIndex, number);
        mapValidator.validate(newMap);
        gameState.setCurrentMap(newMap);

        mapPrinter.printMap(newMap);
    }

}
