package hu.nye.progtech.sudoku.model;

public enum Box {

    TOP_LEFT(1, 1),
    TOP_MIDDLE(1, 3),
    TOP_RIGHT(1, 6),
    MIDDLE_LEFT(3, 1),
    CENTER(3, 3),
    MIDDLE_RIGHT(3, 6),
    BOTTOM_LEFT(6, 1),
    BOTTOM_MIDDLE(6, 3),
    BOTTOM_RIGHT(6, 6);

    public static final int COLUMN_COUNT = 3;
    public static final int ROW_COUNT = 3;

    private final int startAtRow;

    private final int startAtColumn;

    Box(int x, int y) {
        this.startAtRow = x;
        this.startAtColumn = y;
    }

    public int getStartAtRow() {
        return startAtRow;
    }

    public int getStartAtColumn() {
        return startAtColumn;
    }
}
