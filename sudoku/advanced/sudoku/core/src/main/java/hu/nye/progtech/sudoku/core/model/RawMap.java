package hu.nye.progtech.sudoku.core.model;

import java.util.Objects;

/**
 * Raw representation of a Sudoku map.
 * The actual map state is stored in {@link String} values. This model class is mainly used for persist
 * a given game state.
 */
public class RawMap {

    private String map;
    private String fixed;

    public RawMap(String map, String fixed) {
        this.map = map;
        this.fixed = fixed;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getFixed() {
        return fixed;
    }

    public void setFixed(String fixed) {
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
        RawMap rawMap = (RawMap) o;
        return map.equals(rawMap.map) && fixed.equals(rawMap.fixed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map, fixed);
    }

    @Override
    public String toString() {
        return "RawMap{" +
            "map='" + map + '\'' +
            ", fixed='" + fixed + '\'' +
            '}';
    }

}
