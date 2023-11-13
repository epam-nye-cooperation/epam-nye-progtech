package hu.nye.progtech.service;

import java.util.Currency;

import hu.nye.progtech.model.Money;

public interface MonateryService {

    Money convertTo(Money moneyToConvert, Currency toCurrency);

}
