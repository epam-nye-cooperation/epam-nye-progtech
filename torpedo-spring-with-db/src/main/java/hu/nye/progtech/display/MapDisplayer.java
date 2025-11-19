package hu.nye.progtech.display;

import java.util.Set;

import hu.nye.progtech.domain.GameMap;
import hu.nye.progtech.domain.RocketDestination;
import hu.nye.progtech.domain.Ship;
import hu.nye.progtech.service.ConsoleService;
import org.springframework.stereotype.Component;

@Component
public class MapDisplayer {
    private final ConsoleService consoleService;

    public MapDisplayer(final ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    public void displayMap(final GameMap gameMap) {
        final int size = gameMap.getSize();
        final Ship ship = gameMap.getShip();
        final char[][] matrix = emptySetup(size);
        addShipHitsToDisplay(ship, matrix);
        addMissedDestinationToDisplay(matrix, gameMap.getMissedTargets());
        consoleService.print(getPrettyPrint(size, matrix));
    }

    private void addMissedDestinationToDisplay(final char[][] matrix, final Set<RocketDestination> missedTargets) {
        for (final RocketDestination rocketDestination : missedTargets) {
            matrix[rocketDestination.getRow()][rocketDestination.getCol()] = 'O';
        }
    }

    private String getPrettyPrint(final int size, final char[][] matrix) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                stringBuilder.append(matrix[row][col]).append(" ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    private char[][] emptySetup(final int mapSize) {
        final char[][] matrix = new char[mapSize][mapSize];

        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                matrix[row][col] = '~';
            }
        }

        return matrix;
    }

    private void addShipHitsToDisplay(final Ship ship, final char[][] matrix) {
        for (int i = 0; i < ship.getLength(); i++) {
            int row = ship.getStartRow();
            int col = ship.getStartCol();

            if (ship.isHorizontal()) {
                col = col + i;
            } else {
                row = row + i;
            }

            if (ship.getHits()[i]) {
                matrix[row][col] = 'X';
            }
        }
    }
}
