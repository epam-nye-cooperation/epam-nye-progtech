package hu.nye.progtech.sudoku.core.service.exception;

/**
 * Exception to represent that a column was invalid.
 */
public class InvalidColumnException extends MapValidationException {

    public InvalidColumnException(String message) {
        super(message);
    }

}
