package hu.nye.progtech.domain;

public final class RocketDestination {

    private final int row;
    private final int col;

    public RocketDestination(final int row, final int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
