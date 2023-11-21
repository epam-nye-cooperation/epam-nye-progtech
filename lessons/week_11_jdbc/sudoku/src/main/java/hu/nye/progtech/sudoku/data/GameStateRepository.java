package hu.nye.progtech.sudoku.data;

import hu.nye.progtech.sudoku.model.GameStateModel;

import java.util.List;

public interface GameStateRepository {

    void save(String map, int playerId);

    GameStateModel findById(int id);

    List<GameStateModel> findAllByPlayer(int playedId);
}
