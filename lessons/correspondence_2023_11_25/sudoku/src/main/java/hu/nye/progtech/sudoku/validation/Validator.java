package hu.nye.progtech.sudoku.validation;

import hu.nye.progtech.sudoku.exceptions.InvalidGridException;
import hu.nye.progtech.sudoku.model.Grid;

public interface Validator {

    void validate(Grid grid) throws InvalidGridException;

}
