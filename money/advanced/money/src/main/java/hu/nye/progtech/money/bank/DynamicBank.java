package hu.nye.progtech.money.bank;

import hu.nye.progtech.money.domain.CurrencyPair;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Map;
import java.util.Optional;

@Component
public class DynamicBank implements Bank {

    private static final double HUF_TO_USD_EXCHANGE_RATE = 0.0034;
    private static final double USD_TO_HUF_EXCHANGE_RATE = 250.0;
    private static final Currency USD = Currency.getInstance("USD");
    private static final Currency HUF = Currency.getInstance("HUF");

    private final Map<CurrencyPair, Double> exchangeRates;

    public DynamicBank() {
        this.exchangeRates = Map.of(
                new CurrencyPair(USD, HUF), USD_TO_HUF_EXCHANGE_RATE,
                new CurrencyPair(HUF, USD), HUF_TO_USD_EXCHANGE_RATE
        );
    }

    @Override
    public Optional<Double> getExchangeRate(CurrencyPair currencyPair) {
        return (currencyPair.from().equals(currencyPair.to()))
                ? Optional.of(1D)
                : Optional.ofNullable(
                        exchangeRates.get(currencyPair))
                .map(DynamicBank::calculateDynamicValue);
    }

    /**
     * Placeholder logic for imitating some dynamic changes.
     * @param value {@link Double}
     * @return {@link Double} changed value
     */
    private static Double calculateDynamicValue(Double value) {
        if (LocalDateTime.now().getMinute() % 2 == 0) {
            return value + 1D;
        } else {
            return value - 1D;
        }
    }
}
