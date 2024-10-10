package hu.nye.progtech.money.bank;

import java.math.BigDecimal;
import java.util.Currency;

public interface Bank {

    BigDecimal getExchangeRate(Currency fromCurrency, Currency toCurrency);

}
