package hu.nye.progtech.model.exception;

public class NotHandledCurrencyException extends RuntimeException {

    public NotHandledCurrencyException(String message) {
        super(message);
    }

    public NotHandledCurrencyException(String message, Throwable cause) {
        super(message, cause);
    }
}
