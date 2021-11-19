package hu.nye.progtech.sudoku.configuration;

import java.util.List;

import hu.nye.progtech.sudoku.model.GameState;
import hu.nye.progtech.sudoku.persistence.GameSavesRepository;
import hu.nye.progtech.sudoku.service.command.Command;
import hu.nye.progtech.sudoku.service.command.InputHandler;
import hu.nye.progtech.sudoku.service.command.impl.DefaultCommand;
import hu.nye.progtech.sudoku.service.command.impl.ExitCommand;
import hu.nye.progtech.sudoku.service.command.impl.PrintCommand;
import hu.nye.progtech.sudoku.service.command.impl.PutCommand;
import hu.nye.progtech.sudoku.service.command.impl.SaveCommand;
import hu.nye.progtech.sudoku.service.command.impl.LoadCommand;
import hu.nye.progtech.sudoku.service.command.performer.PutPerformer;
import hu.nye.progtech.sudoku.service.validator.impl.MapValidatorComposer;
import hu.nye.progtech.sudoku.ui.MapPrinter;
import hu.nye.progtech.sudoku.ui.PrintWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandConfiguration {

    @Bean
    public InputHandler inputHandler(List<Command> commandList) {
        return new InputHandler(commandList);
    }

    @Bean
    public PrintCommand printCommand(GameState gameState, MapPrinter mapPrinter) {
        return new PrintCommand(gameState, mapPrinter);
    }

    @Bean
    public PutCommand putCommand(GameState gameState, PutPerformer putPerformer, MapValidatorComposer mapValidatorComposer,
                                 MapPrinter mapPrinter, PrintWrapper printWrapper) {
        return new PutCommand(gameState, putPerformer, mapValidatorComposer, mapPrinter, printWrapper);
    }

    @Bean
    public SaveCommand saveCommand(GameSavesRepository gameSavesRepository, GameState gameState) {
        return new SaveCommand(gameSavesRepository, gameState);
    }

    @Bean
    public LoadCommand loadCommand(GameSavesRepository gameSavesRepository, GameState gameState) {
        return new LoadCommand(gameSavesRepository, gameState);
    }

    @Bean
    public ExitCommand exitCommand(GameState gameState) {
        return new ExitCommand(gameState);
    }

    @Bean
    public DefaultCommand defaultCommand(PrintWrapper printWrapper) {
        return new DefaultCommand(printWrapper);
    }

}
