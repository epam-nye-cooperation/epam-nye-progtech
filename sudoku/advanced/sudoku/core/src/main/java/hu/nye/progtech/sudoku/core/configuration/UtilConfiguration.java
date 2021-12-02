package hu.nye.progtech.sudoku.core.configuration;

import hu.nye.progtech.sudoku.core.service.util.CollectionUtil;
import hu.nye.progtech.sudoku.core.service.util.MapToStringUtil;
import hu.nye.progtech.sudoku.core.service.util.MapUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Java configuration class for utility Spring Beans.
 */
@Configuration
public class UtilConfiguration {

    @Bean
    MapUtil mapUtil() {
        return new MapUtil();
    }

    @Bean
    CollectionUtil collectionUtil() {
        return new CollectionUtil();
    }

    @Bean
    MapToStringUtil mapToStringUtil() {
        return new MapToStringUtil();
    }

}
