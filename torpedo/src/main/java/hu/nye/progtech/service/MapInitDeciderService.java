package hu.nye.progtech.service;

import hu.nye.progtech.init.ConsoleMapInit;
import hu.nye.progtech.init.FileMapInit;
import hu.nye.progtech.init.MapInit;

public class MapInitDeciderService {
    private final ConsoleService consoleService;

    public MapInitDeciderService(final ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    public MapInit getMapInitInstance() {
        final int option = consoleService.readIntFromConsole("Please provide number '1' for manual setup or '2' for loading from a file.");

        return switch (option) {
            case 1 -> new ConsoleMapInit(consoleService);
            case 2 -> new FileMapInit();
            default -> null;
        };
    }
}
