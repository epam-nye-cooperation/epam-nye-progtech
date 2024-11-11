package hu.nye.progtech.money.config;

import hu.nye.progtech.money.bank.Bank;
import hu.nye.progtech.money.bank.DynamicBank;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MoneyAppConfig {

    @Bean
//    @Primary
// Ha több azonos típusú objektumunk van és biztosak vagyunk benne, hogy pont ezt szeretnénk használni alapértelmezetten.
    public Bank createExtraDynamicBank() {
        return new DynamicBank();
    }

}
