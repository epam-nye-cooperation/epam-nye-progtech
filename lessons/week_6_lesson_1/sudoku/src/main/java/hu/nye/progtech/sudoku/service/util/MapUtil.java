package hu.nye.progtech.sudoku.service.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import hu.nye.progtech.sudoku.model.BoxDescription;
import hu.nye.progtech.sudoku.model.MapVO;

public class MapUtil {

    public List<Integer> getRowWithIndex(MapVO mapVO, int rowIndex) {
        Objects.requireNonNull(mapVO, "MapVO is a mandatory parameter");
        List<Integer> result = new ArrayList<>();

        int[][] map = mapVO.getMap();
        for (int i = 0; i < mapVO.getNumberOfColumns(); i++) {
            result.add(map[rowIndex][i]);
        }

        return result;
    }

    public List<Integer> getColumnWithIndex(MapVO mapVO, int columnIndex) {
        List<Integer> result = new ArrayList<>();

        int[][] map = mapVO.getMap();
        for (int i = 0; i < mapVO.getNumberOfRows(); i++) {
            result.add(map[i][columnIndex]);
        }

        return result;
    }

    public List<Integer> getBoxValues(MapVO mapVO, BoxDescription boxDescription) {
        List<Integer> result = new ArrayList<>();

        int[][] map = mapVO.getMap();
        int minRowIndex = boxDescription.getMinRowIndex();
        int maxRowIndex = boxDescription.getMaxRowIndex();
        int minColumnIndex = boxDescription.getMinColumnIndex();
        int maxColumnIndex = boxDescription.getMaxColumnIndex();

        for (int i = minRowIndex; i < maxRowIndex; i++) {
            for (int j = minColumnIndex; j < maxColumnIndex; j++) {
                result.add(map[i][j]);
            }
        }

        return result;
    }

}
