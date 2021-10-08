package hu.nye.progtech.sudoku;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import hu.nye.progtech.sudoku.model.BoxDescription;
import hu.nye.progtech.sudoku.model.GameState;
import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.service.command.Command;
import hu.nye.progtech.sudoku.service.command.InputHandler;
import hu.nye.progtech.sudoku.service.command.impl.DefaultCommand;
import hu.nye.progtech.sudoku.service.command.impl.ExitCommand;
import hu.nye.progtech.sudoku.service.command.impl.PrintCommand;
import hu.nye.progtech.sudoku.service.command.impl.PutCommand;
import hu.nye.progtech.sudoku.service.command.performer.PutPerformer;
import hu.nye.progtech.sudoku.service.game.GameController;
import hu.nye.progtech.sudoku.service.game.GameStepPerformer;
import hu.nye.progtech.sudoku.service.input.UserInputReader;
import hu.nye.progtech.sudoku.service.map.MapReaderFacade;
import hu.nye.progtech.sudoku.service.map.parser.MapParser;
import hu.nye.progtech.sudoku.service.map.reader.MapReader;
import hu.nye.progtech.sudoku.service.map.reader.impl.BufferedReaderMapReader;
import hu.nye.progtech.sudoku.service.util.CollectionUtil;
import hu.nye.progtech.sudoku.service.util.MapUtil;
import hu.nye.progtech.sudoku.service.validator.MapValidator;
import hu.nye.progtech.sudoku.service.validator.impl.MapByBoxValidator;
import hu.nye.progtech.sudoku.service.validator.impl.MapByColumnValidator;
import hu.nye.progtech.sudoku.service.validator.impl.MapByRowValidator;
import hu.nye.progtech.sudoku.service.validator.impl.MapValidatorComposer;
import hu.nye.progtech.sudoku.ui.MapPrinter;
import hu.nye.progtech.sudoku.ui.PrintWrapper;

/**
 * Entry point of the Sudoku game.
 */
public class Main {

    /**
     * Entrypoint of the game.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // game components
        GameState gameState = new GameState(null, false);

        InputStream is = Main.class.getClassLoader().getResourceAsStream("map/beginner.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        MapReader mapReader = new BufferedReaderMapReader(reader);

        CollectionUtil collectionUtil = new CollectionUtil();
        MapUtil mapUtil = new MapUtil();
        PrintWrapper printWrapper = new PrintWrapper();

        List<MapValidator> mapValidatorList = List.of(
            new MapByRowValidator(collectionUtil, mapUtil),
            new MapByColumnValidator(collectionUtil, mapUtil),
            new MapByBoxValidator(BoxDescription.BOX_DESCRIPTION_LIST, mapUtil, collectionUtil)
        );
        MapValidatorComposer mapValidatorComposer = new MapValidatorComposer(mapValidatorList);

        MapParser mapParser = new MapParser(9, 9);

        MapReaderFacade mapReaderFacade = new MapReaderFacade(mapReader, mapParser, mapValidatorComposer);
        MapVO mapVO = mapReaderFacade.readMap();
        gameState.setCurrentMap(mapVO);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        UserInputReader userInputReader = new UserInputReader(bufferedReader);

        MapPrinter mapPrinter = new MapPrinter(3, 3, mapUtil, printWrapper);
        PutPerformer putPerformer = new PutPerformer();

        List<Command> commandList = List.of(
            new PrintCommand(gameState, mapPrinter),
            new PutCommand(gameState, putPerformer, mapValidatorComposer, mapPrinter, printWrapper),
            new ExitCommand(gameState),
            new DefaultCommand(printWrapper)
        );
        InputHandler inputHandler = new InputHandler(commandList);

        GameStepPerformer gameStepPerformer = new GameStepPerformer(userInputReader, inputHandler);

        // game
        GameController gameController = new GameController(gameState, gameStepPerformer, mapUtil);
        gameController.start();
    }

}
