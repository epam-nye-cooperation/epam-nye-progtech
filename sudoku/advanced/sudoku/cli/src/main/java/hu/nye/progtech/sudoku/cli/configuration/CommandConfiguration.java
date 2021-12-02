package hu.nye.progtech.sudoku.cli.configuration;

import java.util.List;

import hu.nye.progtech.sudoku.cli.game.GameStateHolder;
import hu.nye.progtech.sudoku.core.model.GameState;
import hu.nye.progtech.sudoku.core.persistence.api.GameSavesRepository;
import hu.nye.progtech.sudoku.cli.command.Command;
import hu.nye.progtech.sudoku.cli.command.InputHandler;
import hu.nye.progtech.sudoku.cli.command.impl.DefaultCommand;
import hu.nye.progtech.sudoku.cli.command.impl.ExitCommand;
import hu.nye.progtech.sudoku.cli.command.impl.LoadCommand;
import hu.nye.progtech.sudoku.cli.command.impl.PrintCommand;
import hu.nye.progtech.sudoku.cli.command.impl.PutCommand;
import hu.nye.progtech.sudoku.cli.command.impl.SaveCommand;
import hu.nye.progtech.sudoku.core.service.GameSavesService;
import hu.nye.progtech.sudoku.core.service.GameStepService;
import hu.nye.progtech.sudoku.core.service.validator.impl.MapValidatorComposer;
import hu.nye.progtech.sudoku.cli.printer.MapPrinter;
import hu.nye.progtech.sudoku.cli.printer.PrintWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Java configuration class for command specific Spring Beans.
 */
@Configuration
public class CommandConfiguration {

    @Bean
    InputHandler inputHandler(List<Command> commandList) {
        return new InputHandler(commandList);
    }

    @Bean
    PrintCommand printCommand(GameStateHolder gameStateHolder, MapPrinter mapPrinter) {
        return new PrintCommand(gameStateHolder, mapPrinter);
    }

    @Bean
    PutCommand putCommand(GameStateHolder gameStateHolder, GameStepService gameStepService, MapValidatorComposer mapValidatorComposer,
                          MapPrinter mapPrinter, PrintWrapper printWrapper) {
        return new PutCommand(gameStateHolder, gameStepService, mapValidatorComposer, mapPrinter, printWrapper);
    }

    @Bean
    SaveCommand saveCommand(GameStateHolder gameStateHolder, GameSavesService gameSavesService) {
        return new SaveCommand(gameStateHolder, gameSavesService);
    }

    @Bean
    LoadCommand loadCommand(GameStateHolder gameStateHolder, GameSavesService gameSavesService) {
        return new LoadCommand(gameStateHolder, gameSavesService);
    }

    @Bean
    ExitCommand exitCommand(GameStateHolder gameStateHolder) {
        return new ExitCommand(gameStateHolder);
    }

    @Bean
    DefaultCommand defaultCommand(PrintWrapper printWrapper) {
        return new DefaultCommand(printWrapper);
    }

}
