package hu.nye.progtech.money;

import java.util.Currency;
import java.util.Optional;

public class WebBank implements BankService {
    @Override
    public Optional<Money> convertTo(Money moneyToConvert, Currency toCurrency) {
        // TODO Implement this web based functionality
        return Optional.empty();
    }
}
