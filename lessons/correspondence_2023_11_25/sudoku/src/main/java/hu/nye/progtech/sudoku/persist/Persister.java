package hu.nye.progtech.sudoku.persist;

import hu.nye.progtech.sudoku.exceptions.GridReadException;
import hu.nye.progtech.sudoku.exceptions.GridWriteException;
import hu.nye.progtech.sudoku.exceptions.InvalidGridException;

public interface Persister {

    int[][] readCells() throws GridReadException, InvalidGridException;

    void writeGrid(int[][] cells) throws GridWriteException;

}
