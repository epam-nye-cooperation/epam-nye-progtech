package hu.nye.progtech.money;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Money money1 = new Money(1000.0, Currency.getInstance("HUF"));
        Money money2 = new Money(1000.0, Currency.getInstance("HUF"));
        Money money3 = new Money(1000.0, Currency.getInstance("HUF"));
        WebBank webBank = new WebBank();
        StaticBank staticBank = new StaticBank();

        Money newMoney = money1.add(money2, staticBank);

        System.out.println(money1.toString());
        System.out.println(newMoney);
        System.out.println(money2.equals(money3));

        // -----------------------

        Money price = new Money(100, Currency.getInstance("USD"));
        Product tv = new Product("TV", price);
        System.out.println(tv);

        // -----------------------

        Money usd = new Money(1, Currency.getInstance("USD"));
        System.out.println(money1.add(usd, staticBank));

        // -----------------------

        Money m1 = new Money(1, Currency.getInstance("USD"));
        Money m2 = new Money(250, Currency.getInstance("HUF"));
        Money m3 = new Money(240, Currency.getInstance("HUF"));
        List<Money> moneyList = new ArrayList<>();
        moneyList.add(m2);
        moneyList.add(m3);
        moneyList.add(m1);
        System.out.println(moneyList);
        moneyList.sort(new MoneyComparator(staticBank));
        System.out.println(moneyList);
    }

}
