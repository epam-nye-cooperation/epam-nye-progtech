package hu.nye.progtech.money.comparator;

import java.math.BigDecimal;
import java.util.Comparator;

import hu.nye.progtech.money.bank.Bank;
import hu.nye.progtech.money.domain.Money;

public class MoneyComparator implements Comparator<Money> {

    private final Bank bank;

    public MoneyComparator(Bank bank) {
        this.bank = bank;
    }

    @Override
    public int compare(Money money1, Money money2) {
        BigDecimal exchangeRate = bank.getExchangeRate(money2.getCurrency(), money1.getCurrency());
        BigDecimal convertedValue = money2.getValue().multiply(exchangeRate);

        return money1.getValue().compareTo(convertedValue);
    }
}
