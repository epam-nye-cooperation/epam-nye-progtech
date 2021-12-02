package hu.nye.progtech.sudoku.cli.command.impl;

import hu.nye.progtech.sudoku.cli.game.GameStateHolder;
import hu.nye.progtech.sudoku.core.model.GameState;
import hu.nye.progtech.sudoku.core.persistence.api.GameSavesRepository;
import hu.nye.progtech.sudoku.cli.command.Command;
import hu.nye.progtech.sudoku.core.service.GameSavesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command used to load a previously saved game state.
 */
public class LoadCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoadCommand.class);
    private static final String LOAD_COMMAND = "load";

    private GameStateHolder gameStateHolder;
    private GameSavesService gameSavesService;

    public LoadCommand(GameStateHolder gameStateHolder, GameSavesService gameSavesService) {
        this.gameStateHolder = gameStateHolder;
        this.gameSavesService = gameSavesService;
    }

    @Override
    public boolean canProcess(String input) {
        return LOAD_COMMAND.equals(input);
    }

    @Override
    public void process(String input) {
        LOGGER.debug("Load command was called");
        gameStateHolder.setGameState(gameSavesService.load(GameStateHolder.CLI_USERNAME));
        LOGGER.info("Load was successful");
    }

}
