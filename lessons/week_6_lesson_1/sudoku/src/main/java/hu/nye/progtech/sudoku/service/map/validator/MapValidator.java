package hu.nye.progtech.sudoku.service.map.validator;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.service.exception.MapValidationException;

public interface MapValidator {

    void validate(MapVO mapVO) throws MapValidationException;

}
