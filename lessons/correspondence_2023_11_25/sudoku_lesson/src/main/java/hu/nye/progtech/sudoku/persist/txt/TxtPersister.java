package hu.nye.progtech.sudoku.persist.txt;

import hu.nye.progtech.sudoku.exceptions.GridReadException;
import hu.nye.progtech.sudoku.exceptions.GridWriteException;
import hu.nye.progtech.sudoku.persist.LineBuilder;
import hu.nye.progtech.sudoku.persist.LineParser;
import hu.nye.progtech.sudoku.persist.Persister;

public class TxtPersister implements Persister {
    @Override public int[][] readCells() throws GridReadException {
        return new LineParser().parseLines(new TxtRepository().readLines());
    }

    @Override public void writeCells(int[][] cells) throws GridWriteException {
        new TxtRepository().writeLines(new LineBuilder().buildLines(cells));
    }
}
