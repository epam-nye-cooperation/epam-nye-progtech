package hu.nye.progtech.model;


import java.util.Currency;
import java.util.Objects;

import hu.nye.progtech.service.MonateryService;

public class Money {

    private double value;
    private final Currency currency;

    public Money(double value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public double getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Money add(Money moneyToAdd, MonateryService monateryService) {
        Money convertedMoney = monateryService.convertTo(moneyToAdd, this.getCurrency());
        this.value += convertedMoney.getValue();
        return this;
    }

    public Integer compareTo(final Money money) {
        if (money == null) {
            return null;
        }
        return Double.compare(this.getValue(), money.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return Double.compare(money.value, value) == 0 && Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Money{");
        sb.append("value=").append(value);
        sb.append(", currency=").append(currency);
        sb.append('}');
        return sb.toString();
    }
}
