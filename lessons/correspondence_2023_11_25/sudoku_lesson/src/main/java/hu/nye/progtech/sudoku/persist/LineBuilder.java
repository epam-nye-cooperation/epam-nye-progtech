package hu.nye.progtech.sudoku.persist;

public class LineBuilder {

    public String[] buildLines(int[][] cells) {
        String[] lines = new String[cells.length];
        for (int i = 0; i < cells.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < cells[i].length; j++) {
                stringBuilder.append(cells[i][j]);
            }
            lines[i] = stringBuilder.toString();
        }
        return lines;
    }

}
