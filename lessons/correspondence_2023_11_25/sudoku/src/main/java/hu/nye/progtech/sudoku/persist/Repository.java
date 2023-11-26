package hu.nye.progtech.sudoku.persist;

import hu.nye.progtech.sudoku.exceptions.GridReadException;
import hu.nye.progtech.sudoku.exceptions.GridWriteException;

public interface Repository {

    String[] readLines() throws GridReadException;

    void writeLines(String[] lines) throws GridWriteException;

}
