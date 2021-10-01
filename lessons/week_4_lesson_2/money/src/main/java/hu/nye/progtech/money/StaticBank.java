package hu.nye.progtech.money;

import java.math.BigDecimal;
import java.util.Currency;

public class StaticBank implements Bank {

    public Money convertTo(Money moneyToConvert, Currency toCurrency) {
        Money convertedMoney;
        if (moneyToConvert.getCurrency().equals(Currency.getInstance("USD")) && toCurrency.equals(Currency.getInstance("HUF"))) {
            convertedMoney = new Money(moneyToConvert.getValue().multiply(new BigDecimal(0.0034)), Currency.getInstance("USD"));
        } else if (moneyToConvert.getCurrency().equals(Currency.getInstance("HUF")) && toCurrency.equals(Currency.getInstance("USD"))) {
            convertedMoney = new Money(moneyToConvert.getValue().multiply(new BigDecimal(249.3)), Currency.getInstance("HUF"));
        } else {
            return null;
        }
        return convertedMoney;
    }

}
