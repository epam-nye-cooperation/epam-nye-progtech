package hu.nye.progtech.model;

import java.util.Currency;

public enum MoneyCurrenciesEnum {
    USD(Currency.getInstance("USD")),
    HUF(Currency.getInstance("HUF")),
    GBP(Currency.getInstance("GBP"));


    private Currency currency;

    MoneyCurrenciesEnum(Currency currency) {
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

}
