package hu.nye.progtech.money;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class Main {

    private static final Currency HUF = Currency.getInstance("HUF");
    private static final Currency USD = Currency.getInstance("USD");

    public static void main(String[] args) {

        BankService bank = new StaticBank();

        Money huf100 = new Money(100D, HUF);
        Money huf1000 = new Money(1000D, HUF);
        Money usd5 = new Money(5D, USD);

        //        System.out.println(huf100.toString());
        //        System.out.println(huf1000);

        /// -------------------------------------------

        //        System.out.println(huf1000.compareTo(huf100));
        //        System.out.println(huf100.add(huf1000, bank).toString());
        //        System.out.println(huf100.add(huf1000, bank));
        //        System.out.println(huf1000.add(usd5, bank));
        //        System.out.println(usd5.add(huf1000, bank));
        System.out.println(usd5.add(null, bank));

        /// -------------------------------------------
        /// Comparisons
        Money m1 = new Money(0.2D, USD);
        Money m2 = new Money(250D, HUF);
        Money m3 = new Money(240D, HUF);
        List<Money> moneyList = new ArrayList<>();
        moneyList.add(m2);
        moneyList.add(m3);
        moneyList.add(m1);
        System.out.println(moneyList);
        moneyList.sort(new MoneyComparator(bank));
        System.out.println(moneyList);
    }
}