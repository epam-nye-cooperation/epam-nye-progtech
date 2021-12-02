package hu.nye.progtech.sudoku.core.service.validator.impl;

import java.util.List;

import hu.nye.progtech.sudoku.core.service.exception.InvalidBoxException;
import hu.nye.progtech.sudoku.core.service.exception.MapValidationException;
import hu.nye.progtech.sudoku.core.service.util.CollectionUtil;
import hu.nye.progtech.sudoku.core.service.util.MapUtil;
import hu.nye.progtech.sudoku.core.model.BoxDescription;
import hu.nye.progtech.sudoku.core.model.MapVO;
import hu.nye.progtech.sudoku.core.service.validator.MapValidator;

/**
 * Validates the boxes of a map. Each box should contain distinct values.
 */
public class MapByBoxValidator implements MapValidator {

    private final List<BoxDescription> boxDescriptionList;
    private final MapUtil mapUtil;
    private final CollectionUtil collectionUtil;

    public MapByBoxValidator(List<BoxDescription> boxDescriptionList, MapUtil mapUtil, CollectionUtil collectionUtil) {
        this.boxDescriptionList = boxDescriptionList;
        this.mapUtil = mapUtil;
        this.collectionUtil = collectionUtil;
    }

    @Override
    public void validate(MapVO mapVO) throws MapValidationException {
        for (BoxDescription boxDescription : boxDescriptionList) {
            List<Integer> boxValuesOfMap = mapUtil.getBoxValuesOfMap(mapVO, boxDescription);
            validateBox(boxValuesOfMap);
        }
    }

    private void validateBox(List<Integer> boxValues) throws InvalidBoxException {
        List<Integer> nonZeroValues = collectionUtil.collectNonZeroValues(boxValues);

        if (!collectionUtil.containsOnlyDistinctValues(nonZeroValues)) {
            throw new InvalidBoxException("A box can only contain distinct values");
        }
    }

}
