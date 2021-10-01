package hu.nye.progtech.money;

import java.math.BigDecimal;
import java.util.Comparator;

public class MoneyComparator implements Comparator<Money> {

    private Bank bank;

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
