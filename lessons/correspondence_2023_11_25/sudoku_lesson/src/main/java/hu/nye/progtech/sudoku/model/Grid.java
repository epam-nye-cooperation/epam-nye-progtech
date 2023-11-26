package hu.nye.progtech.sudoku.model;

import java.util.Arrays;

import hu.nye.progtech.sudoku.exceptions.InvalidGridException;

public class Grid {

    private final int[][] cells;

    public Grid(int[][] cells) throws InvalidGridException {
        if (cells == null || cells.length < 9 || cells[0].length > 9) {
            throw new InvalidGridException("Hibás táblázat!");
        }
        this.cells = copyGrid(cells);
    }

    private int[][] copyGrid(int[][] cells) {
        int[][] result = new int[9][9];
        for (int i = 0; i < 9; i++) {
            result[i] = Arrays.copyOf(cells[i], cells[i].length);
        }
        return result;
    }

    public int[] getRow(int index) {
        return cells[index];
    }

    public int[][] getCells() {
        return cells;
    }

    @Override public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                stringBuilder.append(cells[i][j]);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
