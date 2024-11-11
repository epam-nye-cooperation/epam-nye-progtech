package hu.nye.progtech.money;

import hu.nye.progtech.money.bank.StaticBank;
import hu.nye.progtech.money.domain.Money;
import hu.nye.progtech.money.domain.Product;
import hu.nye.progtech.money.service.MoneyService;
import hu.nye.progtech.money.service.impl.MoneyServiceImpl;

import java.util.Currency;

public class Main {

    public static void main(String[] args) {
        Money money1 = new Money(1000.0, Currency.getInstance("HUF"));
        Money money2 = new Money(2000.0, Currency.getInstance("HUF"));
        Money money3 = new Money(1000.0, Currency.getInstance("HUF"));
        StaticBank staticBank = new StaticBank();
        MoneyService moneyService = new MoneyServiceImpl();

//        Money sumedMoney = moneyService.add(money1, money2, staticBank);
//
//        System.out.println(money1);
//        System.out.println(sumedMoney);
//        System.out.println(money2.equals(money3));

        // -----------------------

//        Money price = new Money(100, Currency.getInstance("USD"));
//        Product tv = new Product("TV", price);
//        System.out.println(tv);
//
//        // -----------------------
//
//        Money usd = new Money(1, Currency.getInstance("USD"));
//        System.out.println(moneyService.add(money1, usd, staticBank));
//
//        // -----------------------
//
        Money m1 = new Money(1, Currency.getInstance("USD"));
        Money m2 = new Money(250.1D, Currency.getInstance("HUF"));
        Money m3 = new Money(250D, Currency.getInstance("HUF"));
        Money m4 = new Money(249.9D, Currency.getInstance("HUF"));
        System.out.printf("Compare %s and %s, result: %s%n", m1, m1, moneyService.compareTo(m1, m1, staticBank));
        System.out.printf("Compare %s and %s, result: %s%n", m2, m1, moneyService.compareTo(m2, m1, staticBank));
        System.out.printf("Compare %s and %s, result: %s%n", m3, m1, moneyService.compareTo(m3, m1, staticBank));
        System.out.printf("Compare %s and %s, result: %s%n", m4, m1, moneyService.compareTo(m4, m1, staticBank));
    }
}
