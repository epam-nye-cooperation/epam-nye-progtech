package hu.nye.progtech.sudoku.persist;

import hu.nye.progtech.sudoku.exceptions.GridReadException;
import hu.nye.progtech.sudoku.exceptions.GridWriteException;
import hu.nye.progtech.sudoku.exceptions.InvalidGridException;

public abstract class AbstractPersister implements Persister {

    @Override public int[][] readCells() throws GridReadException, InvalidGridException {
        return new LineParserImpl().parseLines(getRepository().readLines());
    }

    @Override public void writeGrid(int[][] cells) throws GridWriteException {
        getRepository().writeLines(new LineBuilderImpl().buildLines(cells));
    }

    //  Template method pattern
    protected abstract Repository getRepository();

}
