package hu.nye.progtech.sudoku.game;

import hu.nye.progtech.sudoku.model.Grid;

public class GameResult {

    private final boolean success;

    private final Grid grid;

    public GameResult(boolean success, Grid grid) {
        this.success = success;
        this.grid = grid;
    }

    public boolean isSuccess() {
        return success;
    }

    public Grid getGrid() {
        return grid;
    }

}
