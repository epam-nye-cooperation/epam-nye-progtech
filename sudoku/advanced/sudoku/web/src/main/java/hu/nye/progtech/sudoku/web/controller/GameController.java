package hu.nye.progtech.sudoku.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import hu.nye.progtech.sudoku.core.model.GameState;
import hu.nye.progtech.sudoku.core.model.GameStep;
import hu.nye.progtech.sudoku.core.service.GameStepService;
import hu.nye.progtech.sudoku.core.service.exception.MapValidationException;
import hu.nye.progtech.sudoku.core.service.exception.PutException;
import hu.nye.progtech.sudoku.web.model.GameStateHolder;

@Controller
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class GameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);
    
    private final GameStateHolder gameStateHolder;
    private final GameStepService gameStepService;
    
    public GameController(GameStateHolder gameStateHolder, GameStepService gameStepService) {
        this.gameStateHolder = gameStateHolder;
        this.gameStepService = gameStepService;
    }

    @PostMapping("/start")
    public ModelAndView startAGame(String username) {
        GameState gameState = gameStateHolder.getGameState();
        gameStateHolder.setGameState(new GameState(username, gameState.getCurrentMap(), false));
        return new ModelAndView("game");
    }
 
    @PostMapping("/step")
    public ModelAndView startAGame(GameStep gameStep) {
        try {
            gameStateHolder.setGameState(gameStepService.put(gameStateHolder.getGameState(), gameStep));
        } catch (PutException e) {
            LOGGER.error("Exception while performing game step", e);
        } catch (MapValidationException e) {
            LOGGER.error("Exception while performing game step", e);
        }
        return new ModelAndView("game");
    }
    
}
