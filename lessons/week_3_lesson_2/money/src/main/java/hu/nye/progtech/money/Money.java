package hu.nye.progtech.money;

import java.util.Currency;

public class Money {

    public double value;
    public Currency currency;

    public Money(Double value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public Double getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Money add(Money moneyToAdd, BankService bankService) {
        Money convertedMoney = bankService.convertTo(moneyToAdd, this.getCurrency());
        this.value += convertedMoney.getValue();
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Money{");
        sb.append("value=").append(value);
        sb.append(", currency=").append(currency);
        sb.append('}');
        return sb.toString();
    }
}
