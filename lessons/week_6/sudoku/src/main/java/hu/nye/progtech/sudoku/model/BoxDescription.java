package hu.nye.progtech.sudoku.model;

import java.util.List;
import java.util.Objects;

public final class BoxDescription {

    public static final BoxDescription TOP_LEFT = new BoxDescription(0, 3, 0, 3);
    public static final BoxDescription TOP_MIDDLE = new BoxDescription(0, 3, 3, 6);
    public static final BoxDescription TOP_RIGHT = new BoxDescription(0, 3, 6, 9);

    public static final BoxDescription MIDDLE_LEFT = new BoxDescription(3, 6, 0, 3);
    public static final BoxDescription MIDDLE_MIDDLE = new BoxDescription(3, 6, 3, 6);
    public static final BoxDescription MIDDLE_RIGHT = new BoxDescription(3, 6, 6, 9);

    public static final BoxDescription BOTTOM_LEFT = new BoxDescription(6, 9, 0, 3);
    public static final BoxDescription BOTTOM_MIDDLE = new BoxDescription(6, 9, 3, 6);
    public static final BoxDescription BOTTOM_RIGHT = new BoxDescription(6, 9, 6, 9);

    public static final List<BoxDescription> BOX_DESCRIPTION_LIST = List.of(
        TOP_LEFT, TOP_MIDDLE, TOP_RIGHT,
        MIDDLE_LEFT, MIDDLE_MIDDLE, MIDDLE_RIGHT,
        BOTTOM_LEFT, BOTTOM_MIDDLE, BOTTOM_RIGHT
    );

    private final int minRowIndex;
    private final int maxRowIndex;
    private final int minColumnIndex;
    private final int maxColumnIndex;

    public BoxDescription(int minRowIndex, int maxRowIndex, int minColumnIndex, int maxColumnIndex) {
        this.minRowIndex = minRowIndex;
        this.maxRowIndex = maxRowIndex;
        this.minColumnIndex = minColumnIndex;
        this.maxColumnIndex = maxColumnIndex;
    }

    public int getMinRowIndex() {
        return minRowIndex;
    }

    public int getMaxRowIndex() {
        return maxRowIndex;
    }

    public int getMinColumnIndex() {
        return minColumnIndex;
    }

    public int getMaxColumnIndex() {
        return maxColumnIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BoxDescription that = (BoxDescription) o;
        return minRowIndex == that.minRowIndex && maxRowIndex == that.maxRowIndex && minColumnIndex == that.minColumnIndex && maxColumnIndex == that.maxColumnIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minRowIndex, maxRowIndex, minColumnIndex, maxColumnIndex);
    }

    @Override
    public String toString() {
        return "BoxDescription{" +
            "minRowIndex=" + minRowIndex +
            ", maxRowIndex=" + maxRowIndex +
            ", minColumnIndex=" + minColumnIndex +
            ", maxColumnIndex=" + maxColumnIndex +
            '}';
    }

}
