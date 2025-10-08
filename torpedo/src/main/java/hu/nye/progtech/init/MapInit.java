package hu.nye.progtech.init;

import java.util.Scanner;

import hu.nye.progtech.domain.GameMap;
import hu.nye.progtech.domain.Ship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapInit {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapInit.class);
    private final Scanner scanner;

    public MapInit(final Scanner scanner) {
        this.scanner = scanner;
    }

    public GameMap readMapDetails() {
        final int mapSize = getDetailsInInt("Please provide the map size: ");
        final int shipSize = getDetailsInInt("Please provide the ship size: ");
        final int startRow = getDetailsInInt("Please provide the ship's starting row: ");
        final int startCol = getDetailsInInt("Please provide the ship's starting column: ");
        final boolean isHorizontal = getDetailsInBoolean("Please provide if the ship is horizontal (true/false): ");

        final Ship ship = new Ship(shipSize, startRow, startCol, isHorizontal);
        return new GameMap(mapSize, ship);
    }

    private int getDetailsInInt(final String message) {
        LOGGER.info(message);
        return scanner.nextInt();
    }

    private boolean getDetailsInBoolean(final String message) {
        LOGGER.info(message);
        return scanner.nextBoolean();
    }

}
