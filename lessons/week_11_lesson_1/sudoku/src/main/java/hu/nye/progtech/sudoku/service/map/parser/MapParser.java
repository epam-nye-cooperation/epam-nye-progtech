package hu.nye.progtech.sudoku.service.map.parser;

import java.util.List;
import java.util.regex.Pattern;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.service.exception.MapParsingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parses a raw representation of a map into a {@link MapVO} object.
 */
public class MapParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapParser.class);

    private static final String VALID_ROW_REGEX = "[0-9]+";

    private final int numberOfRows;
    private final int numberOfColumns;

    public MapParser(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    /**
     * Parses a map from a raw representation.
     *
     * @param rows the raw representation of a map
     * @return a parsed map as a {@link MapVO} object
     * @throws MapParsingException if the raw representation of the map was invalid
     */
    public MapVO parseMap(List<String> rows) throws MapParsingException {
        LOGGER.info("Parsing the raw map = {}", rows);

        checkNumberOfRows(rows);
        checkNumberOfColumns(rows);
        checkValues(rows);

        int[][] map = getMap(rows);
        boolean[][] fixed = getFixed(map);

        return new MapVO(numberOfRows, numberOfColumns, map, fixed);
    }

    private void checkNumberOfRows(List<String> rows) throws MapParsingException {
        if (rows.size() != numberOfRows) {
            throw new MapParsingException("Number of rows must be " + numberOfRows);
        }
    }

    private void checkNumberOfColumns(List<String> rows) throws MapParsingException {
        for (String row : rows) {
            if (row.length() != numberOfColumns) {
                throw new MapParsingException("Number of columns must be " + numberOfColumns);
            }
        }
    }

    private void checkValues(List<String> rows) throws MapParsingException {
        for (String row : rows) {
            if (!Pattern.matches(VALID_ROW_REGEX, row)) {
                throw new MapParsingException("Row contains invalid characters!");
            }
        }
    }

    private int[][] getMap(List<String> rows) {
        int[][] map = new int[numberOfRows][numberOfColumns];

        for (int x = 0; x < numberOfRows; x++) {
            String row = rows.get(x);
            String[] numbersAsString = row.split("");
            for (int y = 0; y < numberOfColumns; y++) {
                int n = Integer.parseInt(numbersAsString[y]);
                map[x][y] = n;
            }
        }

        return map;
    }

    private boolean[][] getFixed(int[][] map) {
        boolean[][] fixed = new boolean[numberOfRows][numberOfColumns];

        for (int x = 0; x < numberOfRows; x++) {
            for (int y = 0; y < numberOfColumns; y++) {
                fixed[x][y] = map[x][y] != 0;
            }
        }

        return fixed;
    }

}
