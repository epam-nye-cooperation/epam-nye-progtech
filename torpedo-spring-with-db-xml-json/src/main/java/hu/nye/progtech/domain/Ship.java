package hu.nye.progtech.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class Ship {

    private int length;
    private int startRow;
    private int startCol;
    private boolean isHorizontal;
    private boolean[] hits;

    public Ship(final int length, final int startRow, final int startCol, final boolean isHorizontal) {
        this.hits = new boolean[length];
        this.length = length;
        this.startRow = startRow;
        this.startCol = startCol;
        this.isHorizontal = isHorizontal;
    }

}
