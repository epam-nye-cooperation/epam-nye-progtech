package hu.nye.progtech.money;

import java.util.Currency;
import java.util.Optional;

public interface BankService {

    Money convertTo(Money moneyToConvert, Currency toCurrency);

}
