package hu.nye.progtech.money;

import java.util.Currency;
import java.util.Optional;

public interface BankService {

    Optional<Money> convertTo(Money moneyToConvert, Currency toCurrency);

}
