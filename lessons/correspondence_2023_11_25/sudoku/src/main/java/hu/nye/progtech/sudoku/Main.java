package hu.nye.progtech.sudoku;

import java.util.Scanner;

import hu.nye.progtech.sudoku.conduct.ConductorImpl;
import hu.nye.progtech.sudoku.exceptions.GridReadException;
import hu.nye.progtech.sudoku.exceptions.GridWriteException;
import hu.nye.progtech.sudoku.exceptions.InvalidGridException;
import hu.nye.progtech.sudoku.persist.Persister;
import hu.nye.progtech.sudoku.persist.db.DbPersister;
import hu.nye.progtech.sudoku.persist.txt.TxtPersister;
import hu.nye.progtech.sudoku.validation.ValidatorImpl;

public class Main {

    public static void main(String[] args) throws InvalidGridException, GridReadException, GridWriteException {
        System.out.println("1 TXT 2 DB 3 XML");
        final Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
        char c = scanner.nextLine().charAt(0);
        Persister persister = null;
        do {
            if (c == '1') {
                persister = new TxtPersister();
            } else if (c == '2') {
                persister = new DbPersister();
            } else if (c == '3') {
                persister = new TxtPersister();
            } else {
                System.out.println("Invalid choice.");
                c = scanner.nextLine().charAt(0);
            }
        } while (persister == null);
        new ConductorImpl(persister, new ValidatorImpl()).conduct();
    }

}
