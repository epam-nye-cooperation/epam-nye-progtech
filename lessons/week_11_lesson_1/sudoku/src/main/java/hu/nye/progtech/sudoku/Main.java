package hu.nye.progtech.sudoku;

import java.sql.SQLException;

import hu.nye.progtech.sudoku.model.GameState;
import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.persistence.GameSavesRepository;
import hu.nye.progtech.sudoku.service.game.GameController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Entry point of the Sudoku game.
 */
public class Main {

    /**
     * Entrypoint of the game.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext("hu.nye.progtech.sudoku");
        GameController gameController = context.getBean(GameController.class);
        // gameController.start();

        GameSavesRepository gameSavesRepository = context.getBean(GameSavesRepository.class);
        GameState gameState = context.getBean(GameState.class);

        gameSavesRepository.save(gameState.getCurrentMap());

        MapVO load = gameSavesRepository.load();
        System.out.println(load);
    }

}
