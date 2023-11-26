package hu.nye.progtech.sudoku.persist;

import hu.nye.progtech.sudoku.exceptions.InvalidGridException;
import hu.nye.progtech.sudoku.model.Constants;

public class LineParserImpl implements LineParser {
    @Override public int[][] parseLines(String[] lines) throws InvalidGridException {
        checkRowNumber(lines);
        int[][] cells = new int[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            String[] digits = lines[i].split("");
            if (digits.length != Constants.COLUMN_COUNT) {
                throw new InvalidGridException("The grid is of invalid size!");
            }
            for (int j = 0; j < digits.length; j++) {
                cells[i][j] = Integer.parseInt(digits[j]);
            }
        }
        return cells;
    }

    private void checkRowNumber(String[] lines) throws InvalidGridException {
        if (lines.length != Constants.ROW_COUNT) {
            throw new InvalidGridException("The grid is of invalid size!");
        }
    }
}
