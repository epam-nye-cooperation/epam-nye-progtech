package hu.nye.progtech.sudoku.model;

import static hu.nye.progtech.sudoku.model.Constants.COLUMN_COUNT;
import static hu.nye.progtech.sudoku.model.Constants.ROW_COUNT;

import java.util.Arrays;

import hu.nye.progtech.sudoku.exceptions.InvalidGridException;

public class Grid {

    private final int[][] cells;

    private final boolean[][] fixed;

    public Grid(int[][] cells) throws InvalidGridException {
        if (cells == null || cells.length < ROW_COUNT || cells[0].length < COLUMN_COUNT) {
            throw new InvalidGridException("The grid cannor be null!");
        }
        this.cells = copyGrid(cells);
        this.fixed = getFixed();
    }

    private int[][] copyGrid(int[][] cells) {
        int[][] result = new int[ROW_COUNT][COLUMN_COUNT];
        for (int i = 0; i < cells.length; i++) {
            result[i] = Arrays.copyOf(cells[i], cells[i].length);
        }
        return result;
    }

    private boolean[][] getFixed() {
        boolean[][] result = new boolean[ROW_COUNT][COLUMN_COUNT];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                result[i][j] = (cells[i][j] != 0);
            }
        }
        return result;
    }

    public int[] getRow(int index) {
        return Arrays.copyOf(cells[index], cells[index].length);
    }

    public int[] getColumn(int index) {
        int[] column = new int[cells.length];
        for (int i = 0; i < cells.length; i++) {
            column[i] = cells[i][index];
        }
        return column;
    }

    public int[] getBoxContent(Box box) {
        int[] boxContent = new int[cells.length];
        for (int i = 0; i < Box.ROW_COUNT; i++) {
            for (int j = 0; j < Box.COLUMN_COUNT; j++) {
                boxContent[i * Box.COLUMN_COUNT + j] = cells[i][j];
            }
        }
        return boxContent;
    }

    public int[][] getCells() {
        return cells;
    }

    @Override public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < ROW_COUNT; i++) {
            if (i % 3 == 0) {
                stringBuilder.append("|-----------------------|\n");
            }
            for (int j = 0; j < COLUMN_COUNT; j++) {
                if (j % 3 == 0) {
                    stringBuilder.append("| ");
                }
                if (cells[i][j] == 0) {
                    stringBuilder.append(" ");
                } else {
                    stringBuilder.append(cells[i][j]);
                }
                stringBuilder.append(" ");
            }
            stringBuilder.append("|\n");
        }
        stringBuilder.append("|-----------------------|\n");
        return stringBuilder.toString();
    }

    public boolean isFixed(int rowIndex, int columnIndex) {
        return fixed[rowIndex][columnIndex];
    }

    public int setCell(int rowIndex, int columnIndex, int value) {
        int previousValue = this.cells[rowIndex][columnIndex];
        this.cells[rowIndex][columnIndex] = value;
        return previousValue;
    }

    public boolean isFilled() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
