package hu.nye.progtech.sudoku.model;

import java.util.List;
import java.util.Objects;

/**
 * Model class to represent the boundaries of a box in a classical Sudoku map.
 * Zero-indexed values should be used for the boundaries. The min values are
 * inclusive, while the max values are non-inclusive.
 */
public class BoxDescription {

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

    public static BoxDescriptionBuilder builder() {
        return new BoxDescriptionBuilder();
    }

    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;

    public BoxDescription(int minX, int maxX, int minY, int maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
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
        return minX == that.minX && maxX == that.maxX && minY == that.minY && maxY == that.maxY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minX, maxX, minY, maxY);
    }

    @Override
    public String toString() {
        return "BoxDescription{" +
            "minX=" + minX +
            ", maxX=" + maxX +
            ", minY=" + minY +
            ", maxY=" + maxY +
            '}';
    }

    /**
     * Builder for {@link BoxDescription}.
     */
    public static final class BoxDescriptionBuilder {
        private int minX;
        private int maxX;
        private int minY;
        private int maxY;

        private BoxDescriptionBuilder() {
        }

        public static BoxDescriptionBuilder builder() {
            return new BoxDescriptionBuilder();
        }

        public BoxDescriptionBuilder withMinX(int minX) {
            this.minX = minX;
            return this;
        }

        public BoxDescriptionBuilder withMaxX(int maxX) {
            this.maxX = maxX;
            return this;
        }

        public BoxDescriptionBuilder withMinY(int minY) {
            this.minY = minY;
            return this;
        }

        public BoxDescriptionBuilder withMaxY(int maxY) {
            this.maxY = maxY;
            return this;
        }

        public BoxDescription build() {
            return new BoxDescription(minX, maxX, minY, maxY);
        }
    }

}
