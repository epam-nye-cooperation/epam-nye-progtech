package hu.nye.progtech.sudoku.core.service.validator.impl;

import java.util.List;

import hu.nye.progtech.sudoku.core.service.exception.MapValidationException;
import hu.nye.progtech.sudoku.core.service.validator.MapValidator;
import hu.nye.progtech.sudoku.core.model.MapVO;

/**
 * Ties multiple {@link MapValidator}s together and validates a map through them.
 */
public class MapValidatorComposer implements MapValidator {

    private final List<MapValidator> mapValidatorList;

    public MapValidatorComposer(List<MapValidator> mapValidatorList) {
        this.mapValidatorList = mapValidatorList;
    }

    @Override
    public void validate(MapVO mapVO) throws MapValidationException {
        for (MapValidator mapValidator : mapValidatorList) {
            mapValidator.validate(mapVO);
        }
    }

}
