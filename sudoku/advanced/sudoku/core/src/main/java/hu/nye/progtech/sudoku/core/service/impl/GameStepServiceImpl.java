package hu.nye.progtech.sudoku.core.service.impl;

import hu.nye.progtech.sudoku.core.performer.PutPerformer;
import hu.nye.progtech.sudoku.core.model.GameState;
import hu.nye.progtech.sudoku.core.model.GameStep;
import hu.nye.progtech.sudoku.core.model.MapVO;
import hu.nye.progtech.sudoku.core.service.GameStepService;
import hu.nye.progtech.sudoku.core.service.exception.MapValidationException;
import hu.nye.progtech.sudoku.core.service.exception.PutException;
import hu.nye.progtech.sudoku.core.service.util.MapUtil;
import hu.nye.progtech.sudoku.core.service.validator.MapValidator;

public class GameStepServiceImpl implements GameStepService {

    private final PutPerformer putPerformer;
    private final MapValidator mapValidator;
    private final MapUtil mapUtil;

    public GameStepServiceImpl(PutPerformer putPerformer, MapValidator mapValidator, MapUtil mapUtil) {
        this.putPerformer = putPerformer;
        this.mapValidator = mapValidator;
        this.mapUtil = mapUtil;
    }

    @Override
    public GameState put(GameState gameState, GameStep gameStep) throws PutException, MapValidationException {
        MapVO newMap = putPerformer.perform(gameState.getCurrentMap(), gameStep.getRowIndex(), gameStep.getColumnIndex(), gameStep.getNumber());
        mapValidator.validate(newMap);
        return new GameState(gameState.getUsername(), newMap, mapUtil.isMapCompleted(newMap));
    }

}
