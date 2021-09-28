package hu.nye.progtech.money;

import java.util.Currency;

public interface Bank {

    Money convertTo(Money moneyToConvert, Currency toCurrency);

}
