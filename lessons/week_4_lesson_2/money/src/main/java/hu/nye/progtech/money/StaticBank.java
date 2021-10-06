package hu.nye.progtech.money;

import java.math.BigDecimal;
import java.util.Currency;

public class StaticBank implements Bank {

    @Override
    public BigDecimal getExchangeRate(Currency fromCurrency, Currency toCurrency) {
        BigDecimal exchangeRate;

        if (fromCurrency.equals(toCurrency)) {
            exchangeRate = BigDecimal.ONE;
        } else if (fromCurrency.equals(Currency.getInstance("USD")) && toCurrency.equals(Currency.getInstance("HUF"))) {
            exchangeRate = BigDecimal.valueOf(249.3);
        } else if (fromCurrency.equals(Currency.getInstance("HUF")) && toCurrency.equals(Currency.getInstance("USD"))) {
            exchangeRate = BigDecimal.valueOf(1.0 / 249.3);
        } else {
            throw new RuntimeException("Exchange rate is unknown");
        }

        return exchangeRate;
    }

}
