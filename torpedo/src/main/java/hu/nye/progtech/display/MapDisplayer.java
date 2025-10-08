package hu.nye.progtech.display;

import hu.nye.progtech.domain.GameMap;
import hu.nye.progtech.domain.Ship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"PMD.AtLeastOneConstructor", "PMD.GuardLogStatement", "PMD.UseVarargs"})
public class MapDisplayer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapDisplayer.class);

    public void displayMap(final GameMap gameMap) {
        final int size = gameMap.getSize();
        final Ship ship = gameMap.getShip();
        final char[][] matrix = emptySetup(size);
        addShipHitsToDisplay(ship, matrix);
        LOGGER.info(getPrettyPrint(size, matrix));
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
