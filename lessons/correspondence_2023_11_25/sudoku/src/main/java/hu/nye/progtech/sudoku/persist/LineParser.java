package hu.nye.progtech.sudoku.persist;

import hu.nye.progtech.sudoku.exceptions.InvalidGridException;

public interface LineParser {

    int[][] parseLines(String[] lines) throws InvalidGridException;

}
