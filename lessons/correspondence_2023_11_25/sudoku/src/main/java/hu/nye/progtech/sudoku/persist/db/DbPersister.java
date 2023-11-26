package hu.nye.progtech.sudoku.persist.db;

import hu.nye.progtech.sudoku.persist.AbstractPersister;
import hu.nye.progtech.sudoku.persist.Repository;

public class DbPersister extends AbstractPersister {

    private final DbRepository dbRepository = new DbRepository();

    @Override protected Repository getRepository() {
        return dbRepository;
    }

}
