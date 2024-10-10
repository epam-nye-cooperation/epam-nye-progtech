package hu.nye.progtech.money.bank;

import java.math.BigDecimal;
import java.util.Currency;

import hu.nye.progtech.money.bank.Bank;

public class StaticBank implements Bank {
    private final ExchangeService exchangeService;

    public StaticBank(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @Override
    public BigDecimal getExchangeRate(Currency fromCurrency, Currency toCurrency) {
        return exchangeService.getExchangeRate(fromCurrency, toCurrency);
    }
}
