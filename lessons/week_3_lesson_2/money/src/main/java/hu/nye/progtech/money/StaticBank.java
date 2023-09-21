package hu.nye.progtech.money;

import java.util.Currency;
import java.util.Optional;

public class StaticBank implements BankService {

    private static final Currency USD = Currency.getInstance("USD");
    private static final Currency HUF = Currency.getInstance("HUF");

    @Override
    public Optional<Money> convertTo(final Money moneyToConvert, final Currency toCurrency) {
        Optional<Money> money = Optional.ofNullable(moneyToConvert);
        if (money.map(Money::getCurrency)
                .map(e -> !e.equals(toCurrency))
                .orElse(false)
        ) {
            if (toCurrency.equals(USD) && moneyToConvert.getCurrency().equals(HUF)) {
                money = Optional.of(new Money(moneyToConvert.value * 0.0034, USD));
            } else if (toCurrency.equals(HUF) && moneyToConvert.getCurrency().equals(USD)) {
                money = Optional.of(new Money(moneyToConvert.value * 249.3, HUF));
            } else {
                return Optional.empty();
            }
        }
        return money;
    }
}
