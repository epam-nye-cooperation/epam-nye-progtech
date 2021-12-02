package hu.nye.progtech.sudoku.web.model;

import hu.nye.progtech.sudoku.core.model.GameState;
import hu.nye.progtech.sudoku.core.service.GameInitializerService;

public class GameStateHolder {

    public static final String CLI_USERNAME = "CLI_user";

    private GameState actualGameState;

    public GameStateHolder(GameInitializerService gameInitializerService) {
        this.actualGameState = gameInitializerService.initiate(CLI_USERNAME);
    }

    public GameState getGameState() {
        return this.actualGameState;
    }

    public void setGameState(GameState gameState) {
        this.actualGameState = gameState;
    }

}
