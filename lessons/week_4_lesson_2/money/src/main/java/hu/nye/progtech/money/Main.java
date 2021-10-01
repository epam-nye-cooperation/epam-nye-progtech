package hu.nye.progtech.money;

import java.util.Currency;

public class Main {

    public static void main(String[] args) {
        Money money1 = new Money(1000.0, Currency.getInstance("HUF"));
        Money money2 = new Money(1000.0, Currency.getInstance("HUF"));
        Money money3 = new Money(1000.0, Currency.getInstance("HUF"));
        WebBank webBank = new WebBank();
        StaticBank staticBank = new StaticBank();

        money1.add(money2, staticBank);

        System.out.println(money1.toString());
        System.out.println(money2.equals(money3));
    }

}
