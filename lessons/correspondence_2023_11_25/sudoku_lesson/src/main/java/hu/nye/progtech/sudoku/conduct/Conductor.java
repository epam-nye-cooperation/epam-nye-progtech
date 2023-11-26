package hu.nye.progtech.sudoku.conduct;

import hu.nye.progtech.sudoku.exceptions.GridReadException;
import hu.nye.progtech.sudoku.exceptions.GridWriteException;
import hu.nye.progtech.sudoku.exceptions.InvalidGridException;
import hu.nye.progtech.sudoku.model.Grid;
import hu.nye.progtech.sudoku.persist.Persister;
import hu.nye.progtech.sudoku.validation.Validator;

public class Conductor {

    private final Persister persister;

    private final Validator validator = new Validator();

    public Conductor(Persister persister) {
        this.persister = persister;
    }

    public void conduct() throws GridReadException, GridWriteException, InvalidGridException {
        Grid grid = new Grid(persister.readCells());
        System.out.println(grid);
        validator.valid();
        //  játék
        persister.writeCells(grid.getCells());
    }

}
