package hu.nye.progtech.sudoku.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Model class used to represent a Sudoku map.
 */
public class MapVO {

    public static MapVOBuilder builder() {
        return new MapVOBuilder();
    }

    private final int numberOfRows;
    private final int numberOfColumns;
    private final int[][] map;
    private final boolean[][] fixed;

    public MapVO(int numberOfRows, int numberOfColumns, int[][] map, boolean[][] fixed) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.map = deepCopy(map);
        this.fixed = deepCopy(fixed);
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public int[][] getMap() {
        return deepCopy(map);
    }

    public boolean[][] getFixed() {
        return deepCopy(fixed);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MapVO mapVO = (MapVO) o;
        return numberOfRows == mapVO.numberOfRows && numberOfColumns == mapVO.numberOfColumns
            && Arrays.deepEquals(map, mapVO.map) && Arrays.deepEquals(fixed, mapVO.fixed);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(numberOfRows, numberOfColumns);
        result = 31 * result + Arrays.deepHashCode(map);
        result = 31 * result + Arrays.deepHashCode(fixed);
        return result;
    }

    @Override
    public String toString() {
        return "MapVO{" +
            "numberOfRows=" + numberOfRows +
            ", numberOfColumns=" + numberOfColumns +
            ", map=" + Arrays.toString(map) +
            ", fixed=" + Arrays.toString(fixed) +
            '}';
    }

    private int[][] deepCopy(int[][] array) {
        int[][] result = null;

        if (array != null) {
            result = new int[array.length][];
            for (int i = 0; i < array.length; i++) {
                result[i] = Arrays.copyOf(array[i], array[i].length);
            }
        }

        return result;
    }

    private boolean[][] deepCopy(boolean[][] array) {
        boolean[][] result = null;

        if (array != null) {
            result = new boolean[array.length][];
            for (int i = 0; i < array.length; i++) {
                result[i] = Arrays.copyOf(array[i], array[i].length);
            }
        }

        return result;
    }

    /**
     * Builder for {@link MapVO}.
     */
    public static final class MapVOBuilder {
        private int numberOfRows;
        private int numberOfColumns;
        private int[][] map;
        private boolean[][] fixed;

        private MapVOBuilder() {
        }

        public static MapVOBuilder builder() {
            return new MapVOBuilder();
        }

        public MapVOBuilder withNumberOfRows(int numberOfRows) {
            this.numberOfRows = numberOfRows;
            return this;
        }

        public MapVOBuilder withNumberOfColumns(int numberOfColumns) {
            this.numberOfColumns = numberOfColumns;
            return this;
        }

        public MapVOBuilder withMap(int[][] map) {
            this.map = map;
            return this;
        }

        public MapVOBuilder withFixed(boolean[][] fixed) {
            this.fixed = fixed;
            return this;
        }

        public MapVO build() {
            return new MapVO(numberOfRows, numberOfColumns, map, fixed);
        }
    }

}
