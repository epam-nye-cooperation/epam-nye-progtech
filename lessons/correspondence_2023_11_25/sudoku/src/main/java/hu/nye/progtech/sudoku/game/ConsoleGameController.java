package hu.nye.progtech.sudoku.game;

import java.util.Scanner;

import hu.nye.progtech.sudoku.exceptions.InvalidGridException;
import hu.nye.progtech.sudoku.model.Grid;
import hu.nye.progtech.sudoku.validation.Validator;
import hu.nye.progtech.sudoku.validation.ValidatorImpl;

public class ConsoleGameController implements GameController {

    private final Grid grid;

    private final Validator validator;

    public ConsoleGameController(Grid grid) throws InvalidGridException {
        this.grid = new Grid(grid.getCells());
        this.validator = new ValidatorImpl();
    }

    @Override public GameResult run() {
        final Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
        System.out.println("rowIndex,columnIndex,number");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if ("x".equals(line)) {
                return new GameResult(grid.isFilled(), grid);
            } else {
                String[] parts = line.split(",");
                if (verifyInput(parts)) {
                    processInput(parts);
                    System.out.println(grid);
                }
                System.out.println("rowIndex (0-8),columnIndex (0-8),number (1-9)");
                if (grid.isFilled()) {
                    return new GameResult(true, grid);
                }
            }
        }
        return new GameResult(grid.isFilled(), grid);
    }

    private void processInput(String[] parts) {
        int rowIndex = Integer.parseInt(parts[0]);
        int columnIndex = Integer.parseInt(parts[1]);
        int value = Integer.parseInt(parts[2]);
        int previousValue = grid.setCell(rowIndex, columnIndex, value);
        try {
            validator.validate(grid);
        } catch (InvalidGridException e) {
            System.out.println(e.getMessage());
            grid.setCell(rowIndex, columnIndex, previousValue);
        }
    }

    private boolean verifyInput(String[] parts) {
        try {
            int rowIndex = Integer.parseInt(parts[0]);
            int columnIndex = Integer.parseInt(parts[1]);
            int value = Integer.parseInt(parts[2]);
            if (rowIndex < 0 || rowIndex >= 9 || columnIndex < 0 || columnIndex >= 9) {
                System.out.println("Invalid index!");
                return false;
            } else if (value < 1 || value > 9) {
                System.out.println("Invalid number!");
                return false;
            } else if (grid.isFixed(rowIndex, columnIndex)) {
                System.out.println("Unmodifiable");
                return false;
            } else {
                return true;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid value!");
            return false;
        }

    }

}
