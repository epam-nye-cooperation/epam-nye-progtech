package hu.nye.progtech.sudoku.model;

public enum Box {

    TOP_LEFT(1, 1),

    TOP_MIDDLE(1, 3),

    TOP_RIGHT(1, 6),

    MIDDLE_LEFT(2, 1),

    CENTER(2, 3),

    MIDDLE_RIGHT(2, 6),

    BOTTOM_LEFT(6, 1),

    BOTTOM_MIDDLE(6, 3),

    BOTTOM_RIGHT(6, 6);

    private final int startAtRow;
    private final int startAtColumn;

    Box(int startAtRow, int startAtColumn) {
        this.startAtRow = startAtRow;
        this.startAtColumn = startAtColumn;
    }
}
