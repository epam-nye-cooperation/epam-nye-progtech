package hu.nye.progtech.sudoku.conduct;

import hu.nye.progtech.sudoku.exceptions.GridReadException;
import hu.nye.progtech.sudoku.exceptions.GridWriteException;
import hu.nye.progtech.sudoku.exceptions.InvalidGridException;
import hu.nye.progtech.sudoku.game.ConsoleGameController;
import hu.nye.progtech.sudoku.game.GameController;
import hu.nye.progtech.sudoku.game.GameResult;
import hu.nye.progtech.sudoku.model.Grid;
import hu.nye.progtech.sudoku.persist.Persister;
import hu.nye.progtech.sudoku.validation.Validator;

public class ConductorImpl implements Conductor {

    private final Persister persister;

    private final Validator validator;

    private GameController gameController;

    public ConductorImpl(Persister persister, Validator validator) {
        this.persister = persister;
        this.validator = validator;
    }

    @Override public void conduct() throws GridReadException, InvalidGridException, GridWriteException {
        Grid grid = new Grid(persister.readCells());
        validator.validate(grid);
        System.out.println(grid);
        gameController = new ConsoleGameController(grid);
        GameResult result = gameController.run();
        if (result.isSuccess()) {
            System.out.println("Congratulations!");
        }
        persister.writeGrid(result.getGrid().getCells());
    }
}
