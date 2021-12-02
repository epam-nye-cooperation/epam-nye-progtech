package hu.nye.progtech.sudoku.core.service.validator;

import hu.nye.progtech.sudoku.core.service.exception.MapValidationException;
import hu.nye.progtech.sudoku.core.model.MapVO;

/**
 * Interface used to validate a {@link MapVO} object.
 */
public interface MapValidator {

    /**
     * Validates the given {@link MapVO} object.
     *
     * @param mapVO the map to validate
     * @throws MapValidationException if the map is invalid
     */
    void validate(MapVO mapVO) throws MapValidationException;

}
