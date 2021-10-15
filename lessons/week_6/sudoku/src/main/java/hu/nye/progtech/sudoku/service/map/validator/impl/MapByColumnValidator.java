package hu.nye.progtech.sudoku.service.map.validator.impl;

import java.util.List;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.service.exception.MapValidationException;
import hu.nye.progtech.sudoku.service.map.validator.MapValidator;
import hu.nye.progtech.sudoku.service.util.CollectionUtil;
import hu.nye.progtech.sudoku.service.util.MapUtil;

public class MapByColumnValidator implements MapValidator {

    private CollectionUtil collectionUtil;
    private MapUtil mapUtil;

    public MapByColumnValidator(CollectionUtil collectionUtil, MapUtil mapUtil) {
        this.collectionUtil = collectionUtil;
        this.mapUtil = mapUtil;
    }

    @Override
    public void validate(MapVO mapVO) throws MapValidationException {
        for (int i = 0; i < mapVO.getNumberOfColumns(); i++) {
            List<Integer> column = mapUtil.getColumnWithIndex(mapVO, i);
            List<Integer> nonZeroValues = collectionUtil.getNonZeroValues(column);

            if (!collectionUtil.containsOnlyDistinctValues(nonZeroValues)) {
                throw new MapValidationException("Columns can only contain distinct values");
            }
        }
    }

}
