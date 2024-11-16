package hu.nye.progtech.sudoku.service.exception;

/**
 * Exception that should be thrown when map validation fails.
 */
public class MapValidationException extends GameException {

    public MapValidationException(String message) {
        super(message);
    }

}
