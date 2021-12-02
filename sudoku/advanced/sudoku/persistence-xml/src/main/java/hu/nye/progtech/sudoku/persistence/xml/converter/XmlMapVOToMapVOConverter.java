package hu.nye.progtech.sudoku.persistence.xml.converter;

import java.util.List;

import hu.nye.progtech.sudoku.core.model.MapVO;
import hu.nye.progtech.sudoku.persistence.xml.model.XmlFixedRow;
import hu.nye.progtech.sudoku.persistence.xml.model.XmlMapRow;
import hu.nye.progtech.sudoku.persistence.xml.model.XmlMapVO;

/**
 * Component that helps to convert an {@link XmlMapVO} object to {@link MapVO}.
 */
public class XmlMapVOToMapVOConverter {

    /**
     * Converts the given {@link XmlMapVO} object to {@link MapVO}.
     *
     * @param xmlMapVO the {@link XmlMapVO} to convert
     * @return a {@link MapVO} object
     */
    public MapVO convert(XmlMapVO xmlMapVO) {
        int numberOfRows = xmlMapVO.getNumberOfRows();
        int numberOfColumns = xmlMapVO.getNumberOfColumns();
        int[][] map = getMap(xmlMapVO);
        boolean[][] fixed = getFixed(xmlMapVO);

        return new MapVO(numberOfRows, numberOfColumns, map, fixed);
    }

    private int[][] getMap(XmlMapVO xmlMapVO) {
        int numberOfRows = xmlMapVO.getNumberOfRows();
        int numberOfColumns = xmlMapVO.getNumberOfColumns();
        int[][] map = new int[numberOfRows][];
        List<XmlMapRow> rows = xmlMapVO.getMap().getRows();

        for (int i = 0; i < numberOfRows; i++) {
            int[] row = new int[numberOfColumns];
            XmlMapRow xmlMapRow = rows.get(i);
            for (int j = 0; j < numberOfColumns; j++) {
                row[j] = xmlMapRow.getValues().get(j);
            }
            map[i] = row;
        }

        return map;
    }

    private boolean[][] getFixed(XmlMapVO xmlMapVO) {
        int numberOfRows = xmlMapVO.getNumberOfRows();
        int numberOfColumns = xmlMapVO.getNumberOfColumns();
        boolean[][] map = new boolean[numberOfRows][];
        List<XmlFixedRow> rows = xmlMapVO.getFixed().getRows();

        for (int i = 0; i < numberOfRows; i++) {
            boolean[] row = new boolean[numberOfColumns];
            XmlFixedRow xmlFixedRow = rows.get(i);
            for (int j = 0; j < numberOfColumns; j++) {
                row[j] = xmlFixedRow.getValues().get(j);
            }
            map[i] = row;
        }

        return map;
    }

}
