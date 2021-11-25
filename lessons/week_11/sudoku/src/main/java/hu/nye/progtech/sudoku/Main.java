package hu.nye.progtech.sudoku;

import java.sql.SQLException;

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
    }

}
