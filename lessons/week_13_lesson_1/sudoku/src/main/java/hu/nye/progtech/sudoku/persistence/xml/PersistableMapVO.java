package hu.nye.progtech.sudoku.persistence.xml;

import hu.nye.progtech.sudoku.model.MapVO;
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

}
