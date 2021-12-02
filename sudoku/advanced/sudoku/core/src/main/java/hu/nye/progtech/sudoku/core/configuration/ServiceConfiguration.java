package hu.nye.progtech.sudoku.core.configuration;

import hu.nye.progtech.sudoku.core.performer.PutPerformer;
import hu.nye.progtech.sudoku.core.persistence.api.GameSavesRepository;
import hu.nye.progtech.sudoku.core.service.GameSavesService;
import hu.nye.progtech.sudoku.core.service.GameStepService;
import hu.nye.progtech.sudoku.core.service.impl.GameSavesServiceImpl;
import hu.nye.progtech.sudoku.core.service.impl.GameStepServiceImpl;
import hu.nye.progtech.sudoku.core.service.util.MapUtil;
import hu.nye.progtech.sudoku.core.service.validator.MapValidator;
import hu.nye.progtech.sudoku.core.service.validator.impl.MapValidatorComposer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    PutPerformer putPerformer() {
        return new PutPerformer();
    }

    @Bean
    GameStepService gameStepService(PutPerformer putPerformer, MapValidatorComposer mapValidatorComposer, MapUtil mapUtil) {
        return new GameStepServiceImpl(putPerformer, mapValidatorComposer, mapUtil);
    }

    @Bean
    GameSavesService gameSavesService(GameSavesRepository gameSavesRepository, MapValidatorComposer mapValidatorComposer,
                                      MapUtil mapUtil) {
        return new GameSavesServiceImpl(gameSavesRepository, mapValidatorComposer, mapUtil);
    }

}
