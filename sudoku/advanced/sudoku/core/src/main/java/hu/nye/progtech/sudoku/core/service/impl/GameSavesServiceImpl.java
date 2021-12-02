package hu.nye.progtech.sudoku.core.service.impl;

import hu.nye.progtech.sudoku.core.model.GameState;
import hu.nye.progtech.sudoku.core.model.MapVO;
import hu.nye.progtech.sudoku.core.persistence.api.GameSavesRepository;
import hu.nye.progtech.sudoku.core.service.GameSavesService;
import hu.nye.progtech.sudoku.core.service.exception.MapValidationException;
import hu.nye.progtech.sudoku.core.service.util.MapUtil;
import hu.nye.progtech.sudoku.core.service.validator.MapValidator;

public class GameSavesServiceImpl implements GameSavesService {

    private final GameSavesRepository gameSavesRepository;
    private final MapValidator mapValidator;
    private final MapUtil mapUtil;

    public GameSavesServiceImpl(GameSavesRepository gameSavesRepository, MapValidator mapValidator, MapUtil mapUtil) {
        this.gameSavesRepository = gameSavesRepository;
        this.mapValidator = mapValidator;
        this.mapUtil = mapUtil;
    }

    @Override
    public void save(GameState gameState) throws MapValidationException {
        MapVO currentMapVO = gameState.getCurrentMap();
        if(mapUtil.isMapCompleted(currentMapVO)) {
            throw new RuntimeException("Completed Sudoku map cannot be saved");
        }
        mapValidator.validate(currentMapVO);
        gameSavesRepository.save(gameState.getUsername(), gameState.getCurrentMap());
    }

    @Override
    public GameState load(String username) {
        MapVO currentMapVO = gameSavesRepository.load(username);
        return new GameState(username, currentMapVO, false);
    }

}
