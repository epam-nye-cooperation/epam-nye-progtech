package hu.nye.progtech.money;

import java.util.Comparator;

public class MoneyComparator implements Comparator<Money> {

    private BankService bank;

    public MoneyComparator(BankService bank) {
        this.bank = bank;
    }

    @Override
    public int compare(Money money1, Money money2) {
        final Double convertedValue = bank.convertTo(money2, money1.getCurrency()).map(Money::getValue).orElse(0.0);
        return money1.getValue().compareTo(convertedValue);
    }
}
