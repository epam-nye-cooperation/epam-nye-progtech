package hu.nye.progtech.sudoku.persist.txt;

import hu.nye.progtech.sudoku.persist.AbstractPersister;
import hu.nye.progtech.sudoku.persist.Persister;
import hu.nye.progtech.sudoku.persist.Repository;

public class TxtPersister extends AbstractPersister {
    @Override protected Repository getRepository() {
        return new TxtRepository();
    }
}
