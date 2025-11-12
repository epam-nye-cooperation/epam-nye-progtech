package hu.nye.progtech.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public final class Ship {

    private final int length;
    private final int startRow;
    private final int startCol;
    private final boolean isHorizontal;
    private final boolean[] hits;

    public Ship(final int length, final int startRow, final int startCol, final boolean isHorizontal) {
        this.hits = new boolean[length];
        this.length = length;
        this.startRow = startRow;
        this.startCol = startCol;
        this.isHorizontal = isHorizontal;
    }

}
