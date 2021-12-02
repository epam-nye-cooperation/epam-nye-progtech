package hu.nye.progtech.sudoku.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import hu.nye.progtech.sudoku.core.service.GameInitializerService;
import hu.nye.progtech.sudoku.web.model.GameStateHolder;

@Configuration
public class ApplicationConfiguration {

    @Bean
    @Scope("session")
    GameStateHolder gameStateHolder(GameInitializerService gameInitializerService) {
        return new GameStateHolder(gameInitializerService);
    }
    
}
