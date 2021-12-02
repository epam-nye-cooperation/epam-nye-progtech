package hu.nye.progtech.sudoku.core.persistence.api;

import hu.nye.progtech.sudoku.core.model.MapVO;

/**
 * Interface for storing and retrieving current Sudoku game states.
 */
public interface GameSavesRepository {

    void save(String username, MapVO currentMap);

    MapVO load(String username);

}
