package hu.nye.progtech.sudoku.persist;

public class LineParser {

    public int[][] parseLines(String[] lines) {
        int[][] cells = new  int[9][9];
        for (int i = 0; i < 9; i++) {
            String[] digits = lines[i].split("");
            for (int j = 0; j < digits.length; j++) {
                cells[i][j] = Integer.parseInt(digits[j]);
            }
        }
        return cells;
    }

}
