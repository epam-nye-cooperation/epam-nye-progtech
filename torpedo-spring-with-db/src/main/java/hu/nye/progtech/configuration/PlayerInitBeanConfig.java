package hu.nye.progtech.configuration;

import hu.nye.progtech.init.PlayerInit;
import hu.nye.progtech.service.ConsoleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerInitBeanConfig {

    @Bean
    public PlayerInit createPlayerInitBean(ConsoleService consoleService){
        return new PlayerInit(consoleService);
    }
}
