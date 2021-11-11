package hu.nye.progtech.sudoku.service.exception;

/**
 * Exception that should be thrown when the reading of a map fails.
 */
public class MapReadingException extends Exception {

    public MapReadingException(String message) {
        super(message);
    }

}
