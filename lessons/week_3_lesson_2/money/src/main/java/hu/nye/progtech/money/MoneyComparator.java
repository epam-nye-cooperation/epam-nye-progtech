package hu.nye.progtech.money;

import java.util.Comparator;

public class MoneyComparator implements Comparator<Money> {

    private Bank bank;

    public MoneyComparator(Bank bank) {
        this.bank = bank;
    }

    @Override
    public int compare(Money money1, Money money2) {
        money2 = bank.convertTo(money2, money1.getCurrency());
        return money1.getValue().compareTo(money2.getValue());
    }
}
