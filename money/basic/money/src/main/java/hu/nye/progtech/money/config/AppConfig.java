package hu.nye.progtech.money.config;

import hu.nye.progtech.money.bank.Bank;
import hu.nye.progtech.money.bank.StaticBank;
import hu.nye.progtech.money.service.ExchangeService;
import hu.nye.progtech.money.service.MoneyService;
import hu.nye.progtech.money.service.impl.ExchangeServiceImpl;
import hu.nye.progtech.money.service.impl.MoneyServiceImpl;

public final class AppConfig {

    private AppConfig() {
    }

    public static ExchangeService exchangeService() {
       return new ExchangeServiceImpl();
    }

    public static Bank staticBank() {
        return new StaticBank();
    }

    public static MoneyService moneyService() {
        return new MoneyServiceImpl(AppConfig.exchangeService());
    }

}
