package hu.nye.progtech.sudoku.persistence.xml.model;

import java.util.Arrays;
import java.util.Objects;

import hu.nye.progtech.sudoku.core.model.MapVO;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Exact copy of {@link MapVO} that can be used for XML marshalling/unmarshalling.
 */
@XmlRootElement(name = "game_state")
public class PersistableMapVO {

    private int numberOfRows;
    private int numberOfColumns;
    private int[][] map;
    private boolean[][] fixed;

    public PersistableMapVO() {
    }

    public PersistableMapVO(int numberOfRows, int numberOfColumns, int[][] map, boolean[][] fixed) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.map = map;
        this.fixed = fixed;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public boolean[][] getFixed() {
        return fixed;
    }

    public void setFixed(boolean[][] fixed) {
        this.fixed = fixed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersistableMapVO that = (PersistableMapVO) o;
        return numberOfRows == that.numberOfRows && numberOfColumns == that.numberOfColumns &&
                Arrays.equals(map, that.map) && Arrays.equals(fixed, that.fixed);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(numberOfRows, numberOfColumns);
        result = 31 * result + Arrays.hashCode(map);
        result = 31 * result + Arrays.hashCode(fixed);
        return result;
    }
}
