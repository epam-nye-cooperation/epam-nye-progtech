package hu.nye.progtech.money.bank;

import hu.nye.progtech.money.domain.CurrencyPair;

import java.util.Optional;

public interface Bank {

    Optional<Double> getExchangeRate(CurrencyPair currencyPair);

}
