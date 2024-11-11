package hu.nye.progtech.money.ui;

import hu.nye.progtech.money.domain.Money;
import hu.nye.progtech.money.service.MoneyService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Currency;

@ShellComponent
public class MoneyServiceCommand {

    private final MoneyService moneyService;

    public MoneyServiceCommand(MoneyService moneyService) {
        this.moneyService = moneyService;
    }

    @ShellMethod(key = "add", value = "Add two monies")
    public String add(String targetCurrencyName, Double targetValue, String fromCurrencyName, Double fromValue) {
        Money targetMoney = new Money(targetValue, Currency.getInstance(targetCurrencyName));
        Money fromMoney = new Money(fromValue, Currency.getInstance(fromCurrencyName));
        Money result = moneyService.add(targetMoney, fromMoney);
        return String.format("Added %s to %s , result is: %s", targetMoney, fromMoney, result);
    }

    @ShellMethod(key = "compare", value = "Compare two monies")
    public String compare(String targetCurrencyName, Double targetValue, String fromCurrencyName, Double fromValue) {
        Money targetMoney = new Money(targetValue, Currency.getInstance(targetCurrencyName));
        Money fromMoney = new Money(fromValue, Currency.getInstance(fromCurrencyName));
        Integer result = moneyService.compareTo(targetMoney, fromMoney);
        return String.format("Compare %s to %s , result is: %s", targetMoney, fromMoney, result);
    }

}
