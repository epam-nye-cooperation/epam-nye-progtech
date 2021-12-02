package hu.nye.progtech.sudoku.xml.model;

import java.util.List;
import java.util.Objects;

import hu.nye.progtech.sudoku.model.MapVO;
import jakarta.xml.bind.annotation.XmlElement;

/**
 * XML representation of the fixed positions of a {@link MapVO} object.
 */
public class XmlFixed {

    private List<XmlFixedRow> rows;

    public XmlFixed() {
    }

    public XmlFixed(List<XmlFixedRow> rows) {
        this.rows = rows;
    }

    @XmlElement(name = "row")
    public List<XmlFixedRow> getRows() {
        return rows;
    }

    public void setRows(List<XmlFixedRow> rows) {
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
        XmlFixed xmlFixed = (XmlFixed) o;
        return rows.equals(xmlFixed.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows);
    }

}
