package hu.nye.progtech.sudoku.cli.command.impl;

import hu.nye.progtech.sudoku.cli.command.Command;
import hu.nye.progtech.sudoku.cli.game.GameStateHolder;
import hu.nye.progtech.sudoku.core.model.GameState;
import hu.nye.progtech.sudoku.core.persistence.api.GameSavesRepository;
import hu.nye.progtech.sudoku.core.service.GameSavesService;
import hu.nye.progtech.sudoku.core.service.exception.MapValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command used to save the current game state.
 */
public class SaveCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaveCommand.class);
    private static final String SAVE_COMMAND = "save";

    private GameStateHolder gameStateHolder;
    private GameSavesService gameSavesService;

    public SaveCommand(GameStateHolder gameStateHolder, GameSavesService gameSavesService) {
        this.gameStateHolder = gameStateHolder;
        this.gameSavesService = gameSavesService;
    }

    @Override
    public boolean canProcess(String input) {
        return SAVE_COMMAND.equals(input);
    }

    @Override
    public void process(String input) {
        LOGGER.debug("Game Save command was called");
        try {
            gameSavesService.save(gameStateHolder.getGameState());
        } catch (MapValidationException e) {
            LOGGER.error("Exception occurred while saving game Save", e);
            throw new RuntimeException(e);
        }
        LOGGER.info("Game Save was successfully persisted");
    }

}
