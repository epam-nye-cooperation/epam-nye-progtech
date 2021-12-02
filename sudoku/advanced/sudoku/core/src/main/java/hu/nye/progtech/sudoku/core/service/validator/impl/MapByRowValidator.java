package hu.nye.progtech.sudoku.core.service.validator.impl;

import java.util.List;

import hu.nye.progtech.sudoku.core.service.exception.InvalidRowException;
import hu.nye.progtech.sudoku.core.service.exception.MapValidationException;
import hu.nye.progtech.sudoku.core.service.util.CollectionUtil;
import hu.nye.progtech.sudoku.core.service.util.MapUtil;
import hu.nye.progtech.sudoku.core.model.MapVO;
import hu.nye.progtech.sudoku.core.service.validator.MapValidator;

/**
 * Validates the rows of a map. Each row should contain distinct values.
 */
public class MapByRowValidator implements MapValidator {

    private final CollectionUtil collectionUtil;
    private final MapUtil mapUtil;

    public MapByRowValidator(CollectionUtil collectionUtil, MapUtil mapUtil) {
        this.collectionUtil = collectionUtil;
        this.mapUtil = mapUtil;
    }

    @Override
    public void validate(MapVO mapVO) throws MapValidationException {
        for (int i = 0; i < mapVO.getNumberOfRows(); i++) {
            List<Integer> row = mapUtil.getRowOfMap(mapVO, i);
            validateRow(row);
        }
    }

    private void validateRow(List<Integer> row) throws InvalidRowException {
        List<Integer> nonZeroValues = collectionUtil.collectNonZeroValues(row);

        if (!collectionUtil.containsOnlyDistinctValues(nonZeroValues)) {
            throw new InvalidRowException("A row can only contain distinct values");
        }
    }

}
