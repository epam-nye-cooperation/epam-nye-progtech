package hu.nye.progtech.sudoku.core.service.impl;

import hu.nye.progtech.sudoku.core.model.GameState;
import hu.nye.progtech.sudoku.core.service.GameInitializerService;
import hu.nye.progtech.sudoku.core.service.generator.InitialMapGenerator;

public class GameInitializerServiceImpl implements GameInitializerService {

    private final InitialMapGenerator initialMapGenerator;

    public GameInitializerServiceImpl(hu.nye.progtech.sudoku.core.service.generator.InitialMapGenerator initialMapGenerator) {
        this.initialMapGenerator = initialMapGenerator;
    }

    @Override
    public GameState initiate(String username) {
        return new GameState(username, initialMapGenerator.generateMap(), false);
    }

}
