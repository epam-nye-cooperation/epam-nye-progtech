package hu.nye.progtech.service.impl;

import java.util.Currency;
import java.util.Optional;

import hu.nye.progtech.model.Money;
import hu.nye.progtech.model.exception.NotHandledCurrencyException;
import hu.nye.progtech.service.MonateryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StaticBank implements MonateryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StaticBank.class);

    private static final double USD_TO_HUF_RATIO = 0.0034;
    private static final double HUF_TO_USD_RATIO = 249.3;
    private static final Currency USD_CURRENCY = Currency.getInstance("USD");
    private static final Currency HUF_CURRENCY = Currency.getInstance("HUF");

    @Override
    public Money convertTo(Money moneyToConvert, Currency toCurrency) {
        LOGGER.info("Convert from {} to {}", moneyToConvert, toCurrency);
        if (isConvertible(moneyToConvert, toCurrency)) {
            final Currency currency = moneyToConvert.getCurrency();
            if (toCurrency.equals(USD_CURRENCY) && currency.equals(HUF_CURRENCY)) {
                moneyToConvert = new Money(moneyToConvert.getValue() * USD_TO_HUF_RATIO, USD_CURRENCY);
            } else if (toCurrency.equals(HUF_CURRENCY) && currency.equals(USD_CURRENCY)) {
                moneyToConvert = new Money(moneyToConvert.getValue() * HUF_TO_USD_RATIO, HUF_CURRENCY);
            } else {
                throw new NotHandledCurrencyException(
                        String.format("Could not convert currencies from %s to %s",
                                moneyToConvert.getCurrency(), toCurrency.getCurrencyCode()));
            }
        }
        return moneyToConvert;
    }

    private static boolean isConvertible(Money moneyToConvert, Currency toCurrency) {
        return Optional.ofNullable(moneyToConvert)
                .map(Money::getCurrency)
                .map(e -> !e.equals(toCurrency))
                .orElseThrow(() -> new RuntimeException("Could not convert currency from NULL to " + toCurrency));
    }
}
