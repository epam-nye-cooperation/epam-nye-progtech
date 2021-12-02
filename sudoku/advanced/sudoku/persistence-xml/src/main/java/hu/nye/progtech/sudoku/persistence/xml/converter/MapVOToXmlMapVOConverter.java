package hu.nye.progtech.sudoku.persistence.xml.converter;

import java.util.ArrayList;
import java.util.List;

import hu.nye.progtech.sudoku.core.model.MapVO;
import hu.nye.progtech.sudoku.persistence.xml.model.XmlFixed;
import hu.nye.progtech.sudoku.persistence.xml.model.XmlFixedRow;
import hu.nye.progtech.sudoku.persistence.xml.model.XmlMap;
import hu.nye.progtech.sudoku.persistence.xml.model.XmlMapRow;
import hu.nye.progtech.sudoku.persistence.xml.model.XmlMapVO;

/**
 * Component that helps to convert a {@link MapVO} object to {@link XmlMapVO}.
 */
public class MapVOToXmlMapVOConverter {

    /**
     * Converts the given {@link MapVO} object to {@link XmlMapVO}.
     *
     * @param mapVO the {@link MapVO} to convert
     * @return an {@link XmlMapVO} object
     */
    public XmlMapVO convert(MapVO mapVO) {
        int numberOfRows = mapVO.getNumberOfRows();
        int numberOfColumns = mapVO.getNumberOfColumns();
        XmlMap xmlMap = getXmlMap(mapVO);
        XmlFixed xmlFixed = getXmlFixed(mapVO);

        return new XmlMapVO(numberOfRows, numberOfColumns, xmlMap, xmlFixed);
    }

    private XmlMap getXmlMap(MapVO mapVO) {
        int[][] map = mapVO.getMap();
        List<XmlMapRow> rows = new ArrayList<>();

        for (int[] row : map) {
            List<Integer> tmp = new ArrayList<>();
            for (int value : row) {
                tmp.add(value);
            }
            XmlMapRow xmlMapRow = new XmlMapRow(tmp);
            rows.add(xmlMapRow);
        }

        return new XmlMap(rows);
    }

    private XmlFixed getXmlFixed(MapVO mapVO) {
        boolean[][] fixed = mapVO.getFixed();
        List<XmlFixedRow> rows = new ArrayList<>();

        for (boolean[] row : fixed) {
            List<Boolean> tmp = new ArrayList<>();
            for (boolean value : row) {
                tmp.add(value);
            }
            XmlFixedRow xmlFixedRow = new XmlFixedRow(tmp);
            rows.add(xmlFixedRow);
        }

        return new XmlFixed(rows);
    }

}
