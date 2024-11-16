package hu.nye.progtech.sudoku.service.exception;

/**
 * Exception that should be thrown when the reading of a map fails.
 */
public class MapReadingException extends GameException {

    public MapReadingException(String message) {
        this(message, null);
    }

    public MapReadingException(String message, Throwable t) {
        super(message, t);
    }

}
