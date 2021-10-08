package hu.nye.progtech.sudoku.service.map.validator.impl;

import java.util.List;

import hu.nye.progtech.sudoku.model.BoxDescription;
import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.service.exception.MapValidationException;
import hu.nye.progtech.sudoku.service.map.validator.MapValidator;
import hu.nye.progtech.sudoku.service.util.CollectionUtil;
import hu.nye.progtech.sudoku.service.util.MapUtil;

public class MapByBoxValidator implements MapValidator {

    private CollectionUtil collectionUtil;
    private MapUtil mapUtil;
    private List<BoxDescription> boxDescriptionList;

    public MapByBoxValidator(CollectionUtil collectionUtil, MapUtil mapUtil, List<BoxDescription> boxDescriptionList) {
        this.collectionUtil = collectionUtil;
        this.mapUtil = mapUtil;
        this.boxDescriptionList = boxDescriptionList;
    }

    @Override
    public void validate(MapVO mapVO) throws MapValidationException {
        for (BoxDescription boxDescription : boxDescriptionList) {
            List<Integer> boxValues = mapUtil.getBoxValues(mapVO, boxDescription);
            List<Integer> nonZeroValues = collectionUtil.getNonZeroValues(boxValues);

            if (!collectionUtil.containsOnlyDistinctValues(nonZeroValues)) {
                throw new MapValidationException("Boxes can only contain distinct values");
            }
        }
    }

}
