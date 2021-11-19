package hu.nye.progtech.sudoku.service.exception;

/**
 * Exception that should be thrown when map validation fails.
 */
public class MapValidationException extends Exception {

    public MapValidationException(String message) {
        super(message);
    }

}
