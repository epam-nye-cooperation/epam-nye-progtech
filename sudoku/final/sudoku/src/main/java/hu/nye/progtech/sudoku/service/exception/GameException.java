package hu.nye.progtech.sudoku.service.exception;

/**
 * Exception that should be thrown when a put operation fails.
 */
public class GameException extends RuntimeException {

    public GameException(String message) {
        this(message, null);
    }

    public GameException(String message, Throwable t) {
        super(message, t);
    }

}
