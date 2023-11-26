package hu.nye.progtech.sudoku.persist;

import hu.nye.progtech.sudoku.exceptions.GridReadException;
import hu.nye.progtech.sudoku.exceptions.GridWriteException;

public interface Persister {

    int[][] readCells() throws GridReadException;

    void writeCells(int[][] cells) throws GridWriteException;

}
