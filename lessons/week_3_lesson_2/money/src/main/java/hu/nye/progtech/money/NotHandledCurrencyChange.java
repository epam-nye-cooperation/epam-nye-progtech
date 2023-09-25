package hu.nye.progtech.money;

public class NotHandledCurrencyChange extends RuntimeException {
    public NotHandledCurrencyChange(String fromCurrency, String toCurrency) {
        super(createMessage(fromCurrency, toCurrency));
    }

    public NotHandledCurrencyChange(String fromCurrency, String toCurrency, Throwable cause) {
        super(createMessage(fromCurrency, toCurrency), cause);
    }

    private static String createMessage(String fromCurrency, String toCurrency) {
        return String.format("Could not change currency from %s to %s.", fromCurrency, toCurrency);
    }
}
