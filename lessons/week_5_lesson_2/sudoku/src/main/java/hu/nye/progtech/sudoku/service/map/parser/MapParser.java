package hu.nye.progtech.sudoku.service.map.parser;

import hu.nye.progtech.sudoku.model.MapVO;

import java.util.List;

public class MapParser {

    private int numberOfRows;
    private int numberOfColumns;

    public MapParser(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    public MapVO parseMap(List<String> rawMap) {
        int[][] map = getMap(rawMap);
        boolean[][] fixed = getFixed(map);

        return new MapVO(numberOfRows, numberOfColumns, map, fixed);
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
