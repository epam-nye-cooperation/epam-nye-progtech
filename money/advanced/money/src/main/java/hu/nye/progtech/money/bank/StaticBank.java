package hu.nye.progtech.money.bank;

import hu.nye.progtech.money.domain.CurrencyPair;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.Map;
import java.util.Optional;

@Component(value = "staticBank")
public class StaticBank implements Bank {

// Konfiuráció injektálásal definiáljuk ezeket az értékeket
//    private static final double HUF_TO_USD_EXCHANGE_RATE = 0.0034;
//    private static final double USD_TO_HUF_EXCHANGE_RATE = 250.0;
    private static final Currency USD = Currency.getInstance("USD");
    private static final Currency HUF = Currency.getInstance("HUF");

    @Value("${money.bank.ratio.usd-huf:100}")
    private double usdToHufRatio;

    @Value("${money.bank.ratio.huf-usd:1}")
    private double hufToUsdRatio;

    private Map<CurrencyPair, Double> exchangeRates;

    // A Spring Bean elkészítése után fut le.
    @PostConstruct
    public void initExchanges() {
        this.exchangeRates = Map.of(
                new CurrencyPair(USD, HUF), usdToHufRatio,
                new CurrencyPair(HUF, USD), hufToUsdRatio
        );
    }

//    public StaticBank() {
//        this.exchangeRates = Map.of(
//                new CurrencyPair(USD, HUF), usdToHufRatio,
//                new CurrencyPair(HUF, USD), hufToUsdRatio
//        );
//    }

    @Override
    public Optional<Double> getExchangeRate(CurrencyPair currencyPair) {
        return (currencyPair.from().equals(currencyPair.to()))
                ? Optional.of(1D)
                : Optional.ofNullable(
                exchangeRates.get(currencyPair)
        );
    }
}
