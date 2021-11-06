package hu.nye.progtech.sudoku;

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
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("hu.nye.progtech.sudoku");
        GameController gameController = context.getBean(GameController.class);
        gameController.start();
    }

}
