package hu.nye.progtech.sudoku.core.model;

import java.util.Objects;

/**
 * Represents a step of a Sudoku game.
 */
public class GameStep {

    private final int rowIndex;
    private final int columnIndex;
    private final int number;

    public GameStep(int rowIndex, int columnIndex, int number) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.number = number;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameStep gameStep = (GameStep) o;
        return rowIndex == gameStep.rowIndex && columnIndex == gameStep.columnIndex && number == gameStep.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, columnIndex, number);
    }

    @Override
    public String toString() {
        return "GameStep{" +
                "rowIndex=" + rowIndex +
                ", columnIndex=" + columnIndex +
                ", number=" + number +
                '}';
    }

}
