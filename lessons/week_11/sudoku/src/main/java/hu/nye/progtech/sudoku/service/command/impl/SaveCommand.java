package hu.nye.progtech.sudoku.service.command.impl;

import hu.nye.progtech.sudoku.model.GameState;
import hu.nye.progtech.sudoku.persistence.GameSavesRepository;
import hu.nye.progtech.sudoku.service.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaveCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaveCommand.class);
    private static final String SAVE_COMMAND = "save";

    private GameSavesRepository gameSavesRepository;
    private GameState gameState;

    public SaveCommand(GameSavesRepository gameSavesRepository, GameState gameState) {
        this.gameSavesRepository = gameSavesRepository;
        this.gameState = gameState;
    }

    @Override
    public boolean canProcess(String input) {
        return SAVE_COMMAND.equals(input);
    }

    @Override
    public void process(String input) {
        LOGGER.debug("Game Save command was called");
        gameSavesRepository.save(gameState.getCurrentMap());
        LOGGER.info("Game Save was successfully persisted");
    }

}
