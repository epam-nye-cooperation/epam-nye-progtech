package hu.nye.progtech.sudoku.service.exception;

/**
 * Exception that should be thrown when the parsing of a map fails.
 */
public class MapSavingException extends GameException {

    public MapSavingException(String message, Throwable t) {
        super(message, t);
    }

}
