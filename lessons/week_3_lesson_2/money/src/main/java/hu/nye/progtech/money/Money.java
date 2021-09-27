package hu.nye.progtech.money;

import java.util.Currency;

public class Money {

    public double value;
    public Currency currency;

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

    public Money add(Money moneyzToGiveMeh) {
        // Convert
        if (!this.currency.equals(moneyzToGiveMeh.getCurrency())) { // If the two currency does not match
            if (this.getCurrency().equals(Currency.getInstance("USD")) && moneyzToGiveMeh.getCurrency().equals(Currency.getInstance("HUF")))
                moneyzToGiveMeh = new Money(moneyzToGiveMeh.value *0.0034, Currency.getInstance("USD"));
            else if (this.getCurrency().equals(Currency.getInstance("HUF")) && moneyzToGiveMeh.getCurrency().equals(Currency.getInstance("USD")))
                moneyzToGiveMeh = new Money(moneyzToGiveMeh.value *249.3, Currency.getInstance("HUF"));
            else return null;
        }
        this.value += moneyzToGiveMeh.getValue(); // Add value of the parameter to this.val
        return this;
    }

    public Integer compareTo(Money m) {
        if (!this.currency.equals(m.getCurrency())) {
            if (this.getCurrency().equals(Currency.getInstance("USD")) && m.getCurrency().equals(Currency.getInstance("HUF")))
                m = new Money(m.value *0.0034, Currency.getInstance("USD"));
            else if (this.getCurrency().equals(Currency.getInstance("HUF")) && m.getCurrency().equals(Currency.getInstance("USD")))
                m = new Money(m.value *249.3, Currency.getInstance("HUF"));
            else return null;
        }
        return Double.compare(this.getValue(), m.getValue());
    }
}
