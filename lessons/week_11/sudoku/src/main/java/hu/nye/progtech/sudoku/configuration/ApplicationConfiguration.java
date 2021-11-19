package hu.nye.progtech.sudoku.configuration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import hu.nye.progtech.sudoku.Main;
import hu.nye.progtech.sudoku.model.GameState;
import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.service.command.InputHandler;
import hu.nye.progtech.sudoku.service.command.performer.PutPerformer;
import hu.nye.progtech.sudoku.service.game.GameController;
import hu.nye.progtech.sudoku.service.game.GameStepPerformer;
import hu.nye.progtech.sudoku.service.input.UserInputReader;
import hu.nye.progtech.sudoku.service.map.MapReaderFacade;
import hu.nye.progtech.sudoku.service.map.parser.MapParser;
import hu.nye.progtech.sudoku.service.map.reader.MapReader;
import hu.nye.progtech.sudoku.service.map.reader.impl.BufferedReaderMapReader;
import hu.nye.progtech.sudoku.service.util.MapUtil;
import hu.nye.progtech.sudoku.service.validator.impl.MapValidatorComposer;
import hu.nye.progtech.sudoku.ui.MapPrinter;
import hu.nye.progtech.sudoku.ui.PrintWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public GameController gameController(GameState gameState, GameStepPerformer gameStepPerformer, MapUtil mapUtil) {
        return new GameController(gameState, gameStepPerformer, mapUtil);
    }

    @Bean
    public GameState gameState(MapValidatorComposer mapValidatorComposer) {
        InputStream is = Main.class.getClassLoader().getResourceAsStream("map/beginner.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        MapReader mapReader = new BufferedReaderMapReader(reader);

        MapParser mapParser = new MapParser(9, 9);

        MapReaderFacade mapReaderFacade = new MapReaderFacade(mapReader, mapParser, mapValidatorComposer);
        MapVO mapVO = mapReaderFacade.readMap();
        return new GameState(mapVO, false);
    }

    @Bean
    public MapParser mapParser() {
        return new MapParser(9, 9);
    }

    @Bean
    public GameStepPerformer gameStepPerformer(UserInputReader userInputReader, InputHandler inputHandler) {
        return new GameStepPerformer(userInputReader, inputHandler);
    }

    @Bean
    public UserInputReader userInputReader() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return new UserInputReader(bufferedReader);
    }

    @Bean
    public MapPrinter mapPrinter(MapUtil mapUtil, PrintWrapper printWrapper) {
        return new MapPrinter(3, 3, mapUtil, printWrapper);
    }

    @Bean
    public PutPerformer putPerformer() {
        return new PutPerformer();
    }

    @Bean
    public PrintWrapper printWrapper() {
        return new PrintWrapper();
    }

}
