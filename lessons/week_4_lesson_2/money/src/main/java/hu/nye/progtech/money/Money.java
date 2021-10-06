package hu.nye.progtech.money;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public class Money {

    private final BigDecimal value;
    private final Currency currency;

    public Money(double value, Currency currency) {
        this(new BigDecimal(value), currency);
    }

    public Money(BigDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Money add(Money moneyToAdd, Bank bank) {
        BigDecimal exchangeRate = bank.getExchangeRate(moneyToAdd.currency, this.currency);
        BigDecimal convertedMoneyToAdd = moneyToAdd.getValue().multiply(exchangeRate);
        BigDecimal newValue = this.value.add(convertedMoneyToAdd);

        return new Money(newValue, this.currency);
    }

    private boolean isInTheSameCurrency(Money money) {
        return this.currency.equals(money.getCurrency());
    }

    @Override
    public String toString() {
        return "Money{" +
            "value=" + value +
            ", currency=" + currency +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(value, money.value) && Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }
}
