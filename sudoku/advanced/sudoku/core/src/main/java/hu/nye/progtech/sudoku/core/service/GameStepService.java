package hu.nye.progtech.sudoku.core.service;

import hu.nye.progtech.sudoku.core.model.GameState;
import hu.nye.progtech.sudoku.core.model.GameStep;
import hu.nye.progtech.sudoku.core.service.exception.MapValidationException;
import hu.nye.progtech.sudoku.core.service.exception.PutException;

public interface GameStepService {

    GameState put(GameState gameState, GameStep gameStep) throws PutException, MapValidationException;

}
