package hu.nye.progtech.service;

import hu.nye.progtech.init.ConsoleMapInit;
import hu.nye.progtech.init.FileMapInit;
import hu.nye.progtech.init.MapInit;
import org.springframework.stereotype.Service;

@Service
public class MapInitDeciderService {

    private final ConsoleService consoleService;
    private final ConsoleMapInit consoleMapInit;
    private final FileMapInit fileMapInit;

    public MapInitDeciderService(final ConsoleService consoleService, final ConsoleMapInit consoleMapInit,
                                 final FileMapInit fileMapInit) {
        this.consoleService = consoleService;
        this.consoleMapInit = consoleMapInit;
        this.fileMapInit = fileMapInit;
    }

    public MapInit getMapInitInstance() {
        final int option = consoleService.readIntFromConsole("Please provide number '1' for manual setup or '2' for loading from a file.");

        return switch (option) {
            case 1 -> consoleMapInit;
            case 2 -> fileMapInit;
            default -> null;
        };
    }
}
