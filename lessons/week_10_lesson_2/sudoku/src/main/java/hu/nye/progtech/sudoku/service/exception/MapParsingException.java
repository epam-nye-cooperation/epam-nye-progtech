package hu.nye.progtech.sudoku.service.exception;

/**
 * Exception that should be thrown when the parsing of a map fails.
 */
public class MapParsingException extends Exception {

    public MapParsingException(String message) {
        super(message);
    }

}
