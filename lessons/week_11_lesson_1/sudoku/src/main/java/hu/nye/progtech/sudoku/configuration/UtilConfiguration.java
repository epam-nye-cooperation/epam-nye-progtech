package hu.nye.progtech.sudoku.configuration;

import hu.nye.progtech.sudoku.service.util.CollectionUtil;
import hu.nye.progtech.sudoku.service.util.MapToStringUtil;
import hu.nye.progtech.sudoku.service.util.MapUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfiguration {

    @Bean
    public MapUtil mapUtil() {
        return new MapUtil();
    }

    @Bean
    public CollectionUtil collectionUtil() {
        return new CollectionUtil();
    }

    @Bean
    public MapToStringUtil mapToStringUtil() {
        return new MapToStringUtil();
    }

}
