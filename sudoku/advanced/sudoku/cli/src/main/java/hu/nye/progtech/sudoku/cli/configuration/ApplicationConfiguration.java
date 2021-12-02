package hu.nye.progtech.sudoku.cli.configuration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import hu.nye.progtech.sudoku.cli.Main;
import hu.nye.progtech.sudoku.cli.command.InputHandler;
import hu.nye.progtech.sudoku.cli.game.GameController;
import hu.nye.progtech.sudoku.cli.game.GameStateHolder;
import hu.nye.progtech.sudoku.cli.game.GameStepPerformer;
import hu.nye.progtech.sudoku.cli.input.UserInputReader;
import hu.nye.progtech.sudoku.cli.printer.MapPrinter;
import hu.nye.progtech.sudoku.cli.printer.PrintWrapper;
import hu.nye.progtech.sudoku.core.model.GameState;
import hu.nye.progtech.sudoku.core.model.MapVO;
import hu.nye.progtech.sudoku.core.service.GameInitializerService;
import hu.nye.progtech.sudoku.core.service.map.MapReaderFacade;
import hu.nye.progtech.sudoku.core.service.map.parser.MapParser;
import hu.nye.progtech.sudoku.core.service.map.reader.MapReader;
import hu.nye.progtech.sudoku.core.service.map.reader.impl.BufferedReaderMapReader;
import hu.nye.progtech.sudoku.core.service.util.MapUtil;
import hu.nye.progtech.sudoku.core.service.validator.impl.MapValidatorComposer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Java configuration class for generic application related Spring Beans.
 */
@Configuration
public class ApplicationConfiguration {

    @Bean(initMethod = "start")
    GameController gameController(GameStateHolder gameStateHolder, GameStepPerformer gameStepPerformer) {
        return new GameController(gameStateHolder, gameStepPerformer);
    }

    @Bean
    GameStateHolder gameStateHolder(GameInitializerService gameInitializerService) {
        return new GameStateHolder(gameInitializerService);
    }

    @Bean
    GameStepPerformer gameStepPerformer(UserInputReader userInputReader, InputHandler inputHandler) {
        return new GameStepPerformer(userInputReader, inputHandler);
    }

    @Bean
    UserInputReader userInputReader() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return new UserInputReader(bufferedReader);
    }

    @Bean
    MapPrinter mapPrinter(MapUtil mapUtil, PrintWrapper printWrapper) {
        return new MapPrinter(3, 3, mapUtil, printWrapper);
    }

    @Bean
    PrintWrapper printWrapper() {
        return new PrintWrapper();
    }

}
