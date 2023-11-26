package hu.nye.progtech.sudoku.persist.txt;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Scanner;

import hu.nye.progtech.sudoku.exceptions.GridReadException;
import hu.nye.progtech.sudoku.exceptions.GridWriteException;
import hu.nye.progtech.sudoku.model.Constants;
import hu.nye.progtech.sudoku.persist.LineParserImpl;
import hu.nye.progtech.sudoku.persist.Repository;

public class TxtRepository implements Repository {
    @Override public String[] readLines() throws GridReadException {
        try (InputStream in = TxtPersister.class.getClassLoader().getResourceAsStream("beginner.txt");
            Scanner scanner = new Scanner(in).useDelimiter("\\n")) {
            String[] lines = new String[Constants.ROW_COUNT];
            for (int i = 0; i < Constants.ROW_COUNT; i++) {
                lines[i] = scanner.nextLine();
            }
            return lines;
        } catch (Exception e) {
            throw new GridReadException(e.getMessage());
        }
    }

    @Override public void writeLines(String[] lines) throws GridWriteException {
        String path = this.getClass().getClassLoader().getResource(".").getPath() +
            "/" + String.format("beginner_%s.txt", new Date().getTime());
        try (FileOutputStream output = new FileOutputStream(path)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String line : lines) {
                stringBuilder.append(line).append("\n");
            }
            output.write(stringBuilder.toString().getBytes());
            System.out.println(path);
        } catch (Exception e) {
            System.out.println(e);
            throw new GridWriteException(e.getMessage());
        }
    }
}
