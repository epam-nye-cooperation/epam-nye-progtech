package hu.nye.progtech.sudoku;

import hu.nye.progtech.sudoku.service.game.GameController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Entry point of the Sudoku game.
 */
public class Main {

    /**
     * Entrypoint of the game.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("main");
        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/progtech","admin","admin")){
            ApplicationContext context = new AnnotationConfigApplicationContext("hu.nye.progtech.sudoku");
            GameController gameController = context.getBean(GameController.class);
            gameController.start();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage(), sqlException);
        }
    }

}
