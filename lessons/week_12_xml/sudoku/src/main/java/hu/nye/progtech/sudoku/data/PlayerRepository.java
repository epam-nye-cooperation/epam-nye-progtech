package hu.nye.progtech.sudoku.data;

import hu.nye.progtech.sudoku.model.Player;

public interface PlayerRepository {

    Player getPlayer(int id);

}
