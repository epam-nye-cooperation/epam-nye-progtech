package hu.nye.progtech.model;

import java.util.Currency;

public final class MoneyCurrencies {

    private MoneyCurrencies() {
    }

    public static final double USD_TO_HUF_RATIO = 0.0034;
    public static final double HUF_TO_USD_RATIO = 249.3;
    public static final Currency USD_CURRENCY = Currency.getInstance("USD");
    public static final Currency HUF_CURRENCY = Currency.getInstance("HUF");

}
