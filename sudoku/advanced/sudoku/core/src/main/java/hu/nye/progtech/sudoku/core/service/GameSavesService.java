package hu.nye.progtech.sudoku.core.service;

import hu.nye.progtech.sudoku.core.model.GameState;
import hu.nye.progtech.sudoku.core.service.exception.MapValidationException;

public interface GameSavesService {

    void save(GameState gameState) throws MapValidationException;

    GameState load(String username);

}
