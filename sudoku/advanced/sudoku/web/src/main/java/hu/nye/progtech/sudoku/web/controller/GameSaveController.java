package hu.nye.progtech.sudoku.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import hu.nye.progtech.sudoku.core.model.GameState;
import hu.nye.progtech.sudoku.core.service.GameSavesService;
import hu.nye.progtech.sudoku.core.service.exception.MapValidationException;
import hu.nye.progtech.sudoku.web.model.GameStateHolder;

@Controller
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class GameSaveController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);
    
    private final GameStateHolder gameStateHolder;
    private final GameSavesService gameSavesService;
    
    public GameSaveController(GameStateHolder gameStateHolder, GameSavesService gameSavesService) {
        this.gameStateHolder = gameStateHolder;
        this.gameSavesService = gameSavesService;
    }
    
    @PostMapping("/save")
    public ModelAndView saveTheGame() {
        try {
            gameSavesService.save(gameStateHolder.getGameState());
        } catch (MapValidationException e) {
            LOGGER.error("Exception while saving game save", e);
        }
        return new ModelAndView("game");
    }
    
    @PostMapping("/load")
    public ModelAndView loadTheGame() {
        GameState gameState = gameSavesService.load(gameStateHolder.getGameState().getUsername());
        gameStateHolder.setGameState(gameState);
        return new ModelAndView("game");
    }
    
}
