package hu.nye.progtech.sudoku.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import hu.nye.progtech.sudoku.exceptions.InvalidGridException;
import hu.nye.progtech.sudoku.model.Grid;
import hu.nye.progtech.sudoku.model.Box;
import hu.nye.progtech.sudoku.model.Constants;

public class ValidatorImpl implements Validator {
    @Override public void validate(Grid grid) throws InvalidGridException {
        for (int i = 0; i < Constants.ROW_COUNT; i++) {
            check(grid.getRow(i));
        }
        for (int i = 0; i < Constants.COLUMN_COUNT; i++) {
            check(grid.getColumn(i));
        }
        for (Box box : Box.values()) {
            check(grid.getBoxContent(box));
        }
    }

    private void check(int[] digits) throws InvalidGridException {
        List<Integer> nonZeroValues = getNonZeroValues(digits);
        Set<Integer> distinctValues = Set.copyOf(nonZeroValues);
        if (nonZeroValues.size() != distinctValues.size()) {
            throw new InvalidGridException("The lines, rows and boxes should contain distinct values!");
        }
    }

    private List<Integer> getNonZeroValues(int[] digits) {
        List<Integer> list = new ArrayList<>(digits.length);
        for (int digit : digits) {
            if (digit != 0) {
                list.add(digit);
            }
        }
        return list;
    }

}
