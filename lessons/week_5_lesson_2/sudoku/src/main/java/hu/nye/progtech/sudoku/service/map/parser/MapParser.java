package hu.nye.progtech.sudoku.service.map.parser;

import java.util.List;
import java.util.regex.Pattern;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.service.exception.MapParseException;

public class MapParser {

    private static final String VALID_ROW_REGEX = "[0-9]+";

    private int numberOfRows;
    private int numberOfColumns;

    public MapParser(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    public MapVO parseMap(List<String> rawMap) throws MapParseException {
        checkNumberOfRows(rawMap);
        checkNumberOfColumns(rawMap);
        checkForInvalidValues(rawMap);

        int[][] map = getMap(rawMap);
        boolean[][] fixed = getFixed(map);

        return new MapVO(numberOfRows, numberOfColumns, map, fixed);
    }

    private void checkNumberOfRows(List<String> rows) throws MapParseException {
        if (rows.size() != numberOfRows) {
            throw new MapParseException("Number of rows must be " + numberOfRows);
        }
    }

    private void checkNumberOfColumns(List<String> rows) throws MapParseException {
        for (String row : rows) {
            if (row.length() != numberOfColumns) {
                throw new MapParseException("Number of columns must be " + numberOfColumns);
            }
        }
    }

    private void checkForInvalidValues(List<String> rows) throws MapParseException {
        for (String row : rows) {
            if (!Pattern.matches(VALID_ROW_REGEX, row)) {
                throw new MapParseException("Row contains invalid characters");
            }
        }
    }

    private int[][] getMap(List<String> rawMap) {
        int[][] result = new int[numberOfRows][];

        for (int i = 0; i < numberOfRows; i++) {
            result[i] = new int[numberOfColumns];

            String line = rawMap.get(i);
            String[] parts = line.split("");

            for (int j = 0; j < numberOfColumns; j++) {
                int parsed = Integer.parseInt(parts[j]);
                result[i][j] = parsed;
            }
        }

        return result;
    }

    private boolean[][] getFixed(int[][] map) {
        boolean[][] result = new boolean[numberOfRows][];

        for (int i = 0; i < numberOfRows; i++) {
            result[i] = new boolean[numberOfColumns];

            for (int j = 0; j < numberOfColumns; j++) {
                result[i][j] = map[i][j] != 0;
            }
        }

        return result;
    }

}
