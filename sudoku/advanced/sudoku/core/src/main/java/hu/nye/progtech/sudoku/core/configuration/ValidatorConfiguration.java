package hu.nye.progtech.sudoku.core.configuration;

import java.util.List;

import hu.nye.progtech.sudoku.core.model.BoxDescription;
import hu.nye.progtech.sudoku.core.service.util.CollectionUtil;
import hu.nye.progtech.sudoku.core.service.util.MapUtil;
import hu.nye.progtech.sudoku.core.service.validator.MapValidator;
import hu.nye.progtech.sudoku.core.service.validator.impl.MapByBoxValidator;
import hu.nye.progtech.sudoku.core.service.validator.impl.MapByColumnValidator;
import hu.nye.progtech.sudoku.core.service.validator.impl.MapByRowValidator;
import hu.nye.progtech.sudoku.core.service.validator.impl.MapValidatorComposer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Java configuration class for Sudoku table validation specific Spring Beans.
 */
@Configuration
public class ValidatorConfiguration {

    @Bean
    MapValidatorComposer mapValidatorComposer(List<MapValidator> mapValidatorList) {
        return new MapValidatorComposer(mapValidatorList);
    }

    @Bean
    MapValidator mapByRowValidator(CollectionUtil collectionUtil, MapUtil mapUtil) {
        return new MapByRowValidator(collectionUtil, mapUtil);
    }

    @Bean
    MapValidator mapByColumnValidator(CollectionUtil collectionUtil, MapUtil mapUtil) {
        return new MapByColumnValidator(collectionUtil, mapUtil);
    }

    @Bean
    MapValidator mapByBoxValidator(CollectionUtil collectionUtil, MapUtil mapUtil) {
        return new MapByBoxValidator(BoxDescription.BOX_DESCRIPTION_LIST, mapUtil, collectionUtil);
    }

}
