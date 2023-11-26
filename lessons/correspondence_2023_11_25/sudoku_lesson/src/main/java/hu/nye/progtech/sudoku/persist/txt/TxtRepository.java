package hu.nye.progtech.sudoku.persist.txt;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Scanner;

import hu.nye.progtech.sudoku.exceptions.GridReadException;
import hu.nye.progtech.sudoku.exceptions.GridWriteException;
import hu.nye.progtech.sudoku.persist.Repository;

public class TxtRepository implements Repository {
    @Override public String[] readLines() throws GridReadException {
        try (InputStream in = TxtPersister.class.getClassLoader().getResourceAsStream("beginner.txt");
            Scanner scanner = new Scanner(in).useDelimiter("\\n")) {
            String[] lines = new String[9];
            for (int i = 0; i < 9; i++) {
                lines[i] = scanner.nextLine();
            }
            return lines;
        } catch (Exception e) {
            throw new GridReadException(e.getMessage());
        }

    }

    @Override public void writeLines(String[] lines) throws GridWriteException {
        String path = TxtPersister.class.getClassLoader().getResource(".").getPath() +
            "/" + String.format("beginner_%s.txt", new Date().getTime());
        try (FileOutputStream output = new FileOutputStream(path)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < lines.length; i++) {
                stringBuilder.append(lines[i]).append("\n");
            }
            output.write(stringBuilder.toString().getBytes());
        } catch (Exception e) {
            throw new GridWriteException(e.getMessage());
        }
    }
}
