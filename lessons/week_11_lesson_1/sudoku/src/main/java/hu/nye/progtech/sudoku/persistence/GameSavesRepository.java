package hu.nye.progtech.sudoku.persistence;

import hu.nye.progtech.sudoku.model.MapVO;

public interface GameSavesRepository {

    void save(MapVO currentMap);

    MapVO load();

}
