package hu.nye.progtech.sudoku.configuration;

import java.util.List;

import hu.nye.progtech.sudoku.model.BoxDescription;
import hu.nye.progtech.sudoku.service.util.CollectionUtil;
import hu.nye.progtech.sudoku.service.util.MapUtil;
import hu.nye.progtech.sudoku.service.validator.MapValidator;
import hu.nye.progtech.sudoku.service.validator.impl.MapByBoxValidator;
import hu.nye.progtech.sudoku.service.validator.impl.MapByColumnValidator;
import hu.nye.progtech.sudoku.service.validator.impl.MapByRowValidator;
import hu.nye.progtech.sudoku.service.validator.impl.MapValidatorComposer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorConfiguration {

    @Bean
    public MapValidatorComposer mapValidatorComposer(List<MapValidator> mapValidatorList) {
        return new MapValidatorComposer(mapValidatorList);
    }

    @Bean
    public MapValidator mapByRowValidator(CollectionUtil collectionUtil, MapUtil mapUtil) {
        return new MapByRowValidator(collectionUtil, mapUtil);
    }

    @Bean
    public MapValidator mapByColumnValidator(CollectionUtil collectionUtil, MapUtil mapUtil) {
        return new MapByColumnValidator(collectionUtil, mapUtil);
    }

    @Bean
    public MapValidator mapByBoxValidator(CollectionUtil collectionUtil, MapUtil mapUtil) {
        return new MapByBoxValidator(BoxDescription.BOX_DESCRIPTION_LIST, mapUtil, collectionUtil);
    }

}
