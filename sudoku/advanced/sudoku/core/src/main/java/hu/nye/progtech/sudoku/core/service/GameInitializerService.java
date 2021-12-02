package hu.nye.progtech.sudoku.core.service;

import hu.nye.progtech.sudoku.core.model.GameState;

/**
 * Interface for initializing GameStates
 */
public interface GameInitializerService {

    GameState initiate(String username);

}
