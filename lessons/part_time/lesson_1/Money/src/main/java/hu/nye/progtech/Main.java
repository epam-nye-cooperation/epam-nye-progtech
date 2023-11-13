package hu.nye.progtech;

import java.util.Currency;

import hu.nye.progtech.config.AppConfig;
import hu.nye.progtech.model.Money;
import hu.nye.progtech.model.MoneyCurrencies;
import hu.nye.progtech.model.exception.NotHandledCurrencyException;
import hu.nye.progtech.service.MonateryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    // Ez itt a logger, mindig az aktuális osztályhoz kell kérni a loggert.
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Application started...");

        final MonateryService bank = AppConfig.staticBank();

        final Money usd10 = new Money(10, MoneyCurrencies.USD_CURRENCY);
        final Money usd10Second = new Money(10, MoneyCurrencies.USD_CURRENCY);
        final Money usd10Third = new Money(10, MoneyCurrencies.USD_CURRENCY);
        final Money usd5 = new Money(5, MoneyCurrencies.USD_CURRENCY);
        final Money huf1000 = new Money(1000, MoneyCurrencies.HUF_CURRENCY);
        final Money huf100 = new Money(100, MoneyCurrencies.HUF_CURRENCY);
        final Money debtHuf100 = new Money(-100, MoneyCurrencies.HUF_CURRENCY);

        System.out.println(usd5);
        System.out.println(usd5.compareTo(usd5));
        /*
        Mellékhatás keletkezik az aktuális objektumokon,
            módosul a `Money#value` mezőjének az értéke minden `Money.add(...)` metódus hívásnál.

        System.out.println("10USD + 5USD =" + usd10.add(usd5, bank));
        System.out.println("10USD + 1000HUF =" +usd10Second.add(huf1000, bank));
        System.out.println("10USD + 100HUF =" + usd10Third.add(debtHuf100, bank));
         */

        // A `{}` páros helyére kerül behelyettesítésre a paraméter.
        LOGGER.info("10USD + 5USD = {} ", usd10.add(usd5, bank));
        LOGGER.info("10USD + 100HUF = {} ", usd10Second.add(huf1000, bank));
        LOGGER.info("10USD + (-100HUF) = {} ", usd10Third.add(debtHuf100, bank));
        try {
            huf100.add(new Money(1, Currency.getInstance("GBP")), bank);
        } catch (NotHandledCurrencyException e) {
            /*
                Itt nem használjuk a {} párost, a logger nem igényli Thowable objektum esetén.
                Fontos, hogy a teljes kivétel objetumot logoljuk ki és ne csak a kivétel üzenetet,
                mert máskülönben elveszítetnénk a kivétel objektum stacktrace-t
             */
            LOGGER.error("Something went wrong, exception: ", e);
        }
        LOGGER.info("Application finished.");
    }
}
