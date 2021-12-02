package hu.nye.progtech.sudoku.persistence.xml.model;

import java.util.Objects;

import hu.nye.progtech.sudoku.core.model.MapVO;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Simple model class used for XML representation of {@link MapVO}.
 */
@XmlRootElement(name = "mapVO")
@XmlType(propOrder = {"map", "fixed"})
public class XmlMapVO {

    private int numberOfRows;
    private int numberOfColumns;
    private XmlMap map;
    private XmlFixed fixed;

    public XmlMapVO() {
    }

    public XmlMapVO(int numberOfRows, int numberOfColumns, XmlMap map, XmlFixed fixed) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.map = map;
        this.fixed = fixed;
    }

    @XmlAttribute
    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    @XmlAttribute
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public XmlMap getMap() {
        return map;
    }

    public void setMap(XmlMap map) {
        this.map = map;
    }

    public XmlFixed getFixed() {
        return fixed;
    }

    public void setFixed(XmlFixed fixed) {
        this.fixed = fixed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        XmlMapVO xmlMapVO = (XmlMapVO) o;
        return numberOfRows == xmlMapVO.numberOfRows && numberOfColumns == xmlMapVO.numberOfColumns
            && map.equals(xmlMapVO.map) && fixed.equals(xmlMapVO.fixed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfRows, numberOfColumns, map, fixed);
    }

}
