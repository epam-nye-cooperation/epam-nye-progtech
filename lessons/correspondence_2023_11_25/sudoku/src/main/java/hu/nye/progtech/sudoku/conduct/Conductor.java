package hu.nye.progtech.sudoku.conduct;

import hu.nye.progtech.sudoku.exceptions.GridReadException;
import hu.nye.progtech.sudoku.exceptions.GridWriteException;
import hu.nye.progtech.sudoku.exceptions.InvalidGridException;

public interface Conductor {

    void conduct() throws GridReadException, InvalidGridException, GridWriteException;

}
