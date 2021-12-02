package hu.nye.progtech.sudoku.core.configuration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import hu.nye.progtech.sudoku.core.service.GameInitializerService;
import hu.nye.progtech.sudoku.core.service.generator.InitialMapGenerator;
import hu.nye.progtech.sudoku.core.service.generator.statictxt.StaticInitialMapGenerator;
import hu.nye.progtech.sudoku.core.service.impl.GameInitializerServiceImpl;
import hu.nye.progtech.sudoku.core.service.map.MapReaderFacade;
import hu.nye.progtech.sudoku.core.service.map.parser.MapParser;
import hu.nye.progtech.sudoku.core.service.map.reader.MapReader;
import hu.nye.progtech.sudoku.core.service.map.reader.impl.BufferedReaderMapReader;
import hu.nye.progtech.sudoku.core.service.validator.impl.MapValidatorComposer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitializerConfiguration {

    @Bean
    MapParser mapParser() {
        return new MapParser(9, 9);
    }

    @Bean
    InitialMapGenerator initialMapGenerator(MapValidatorComposer mapValidatorComposer, MapParser mapParser) {
        InputStream is = InitializerConfiguration.class.getClassLoader().getResourceAsStream("map/beginner.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        MapReader mapReader = new BufferedReaderMapReader(reader);

        MapReaderFacade mapReaderFacade = new MapReaderFacade(mapReader, mapParser, mapValidatorComposer);
        return new StaticInitialMapGenerator(mapReaderFacade);
    }

    @Bean
    GameInitializerService gameInitializerService(InitialMapGenerator initialMapGenerator) {
        return new GameInitializerServiceImpl(initialMapGenerator);
    }

}
