package hu.nye.progtech.money;

import java.util.Currency;
import java.util.Optional;

public class StaticBank implements BankService {

    private static final Currency USD = Currency.getInstance("USD");
    private static final Currency HUF = Currency.getInstance("HUF");

    @Override
    public Money convertTo(Money moneyToConvert, Currency toCurrency) {
        if (isConvertible(moneyToConvert, toCurrency)) {
            final Currency currency = moneyToConvert.getCurrency();
            if (toCurrency.equals(USD) && currency.equals(HUF)) {
                moneyToConvert = new Money(moneyToConvert.value * 0.0034, USD);
            } else if (toCurrency.equals(HUF) && currency.equals(USD)) {
                moneyToConvert = new Money(moneyToConvert.value * 249.3, HUF);
            } else {
                throw new NotHandledCurrencyChange(currency.getCurrencyCode(), toCurrency.getCurrencyCode());
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
