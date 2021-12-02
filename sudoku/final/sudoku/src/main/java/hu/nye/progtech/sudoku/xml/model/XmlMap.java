package hu.nye.progtech.sudoku.xml.model;

import java.util.List;
import java.util.Objects;

import hu.nye.progtech.sudoku.model.MapVO;
import jakarta.xml.bind.annotation.XmlElement;

/**
 * XML representation of the map state of a {@link MapVO} object.
 */
public class XmlMap {

    private List<XmlMapRow> rows;

    public XmlMap() {
    }

    public XmlMap(List<XmlMapRow> rows) {
        this.rows = rows;
    }

    @XmlElement(name = "row")
    public List<XmlMapRow> getRows() {
        return rows;
    }

    public void setRows(List<XmlMapRow> rows) {
        this.rows = rows;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        XmlMap xmlMap = (XmlMap) o;
        return rows.equals(xmlMap.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows);
    }

}
