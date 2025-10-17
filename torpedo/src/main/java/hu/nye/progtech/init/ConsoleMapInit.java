package hu.nye.progtech.init;

import hu.nye.progtech.domain.GameMap;
import hu.nye.progtech.domain.Ship;
import hu.nye.progtech.service.ConsoleService;

public class ConsoleMapInit implements MapInit {
    private final ConsoleService consoleService;

    public ConsoleMapInit(final ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    @Override
    public GameMap readMapDetails() {
        final int mapSize = consoleService.readIntFromConsole("Please provide the map size: ");
        final int shipSize = consoleService.readIntFromConsole("Please provide the ship size: ");
        final int startRow = consoleService.readIntFromConsole("Please provide the ship's starting row: ");
        final int startCol = consoleService.readIntFromConsole("Please provide the ship's starting column: ");
        final boolean isHorizontal = consoleService.readBooleanFromConsole("Please provide if the ship is horizontal (true/false): ");

        final Ship ship = new Ship(shipSize, startRow, startCol, isHorizontal);
        return new GameMap(mapSize, ship);
    }
}
