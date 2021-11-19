package hu.nye.progtech.sudoku.service.exception;

/**
 * Exception to represent that a row was invalid.
 */
public class InvalidRowException extends MapValidationException {

    public InvalidRowException(String message) {
        super(message);
    }

}
