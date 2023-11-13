package hu.nye.progtech.config;

import hu.nye.progtech.service.MonateryService;
import hu.nye.progtech.service.impl.StaticBank;

public class AppConfig {

    public static MonateryService staticBank() {
        return new StaticBank();
    }

}
