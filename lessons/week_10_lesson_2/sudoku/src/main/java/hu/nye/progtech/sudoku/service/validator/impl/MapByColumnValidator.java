package hu.nye.progtech.sudoku.service.validator.impl;

import java.util.List;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.service.exception.InvalidColumnException;
import hu.nye.progtech.sudoku.service.exception.MapValidationException;
import hu.nye.progtech.sudoku.service.util.CollectionUtil;
import hu.nye.progtech.sudoku.service.util.MapUtil;
import hu.nye.progtech.sudoku.service.validator.MapValidator;

/**
 * Validates the columns of a map. Each column should contain distinct values.
 */
public class MapByColumnValidator implements MapValidator {

    private final CollectionUtil collectionUtil;
    private final MapUtil mapUtil;

    public MapByColumnValidator(CollectionUtil collectionUtil, MapUtil mapUtil) {
        this.collectionUtil = collectionUtil;
        this.mapUtil = mapUtil;
    }

    @Override
    public void validate(MapVO mapVO) throws MapValidationException {
        for (int i = 0; i < mapVO.getNumberOfRows(); i++) {
            List<Integer> column = mapUtil.getColumnOfMap(mapVO, i);
            validateColumn(column);
        }
    }

    private void validateColumn(List<Integer> column) throws InvalidColumnException {
        List<Integer> nonZeroValues = collectionUtil.collectNonZeroValues(column);

        if (!collectionUtil.containsOnlyDistinctValues(nonZeroValues)) {
            throw new InvalidColumnException("A column can only contain distinct values");
        }
    }

}
