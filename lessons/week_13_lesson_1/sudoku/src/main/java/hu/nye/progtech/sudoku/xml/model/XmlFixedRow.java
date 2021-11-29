package hu.nye.progtech.sudoku.xml.model;

import java.util.List;
import java.util.Objects;

import hu.nye.progtech.sudoku.model.MapVO;
import jakarta.xml.bind.annotation.XmlElement;

/**
 * XML representation of a row of the fixed positions from a {@link MapVO} object.
 */
public class XmlFixedRow {

    private List<Boolean> values;

    public XmlFixedRow() {
    }

    public XmlFixedRow(List<Boolean> values) {
        this.values = values;
    }

    @XmlElement(name = "value")
    public List<Boolean> getValues() {
        return values;
    }

    public void setValues(List<Boolean> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        XmlFixedRow that = (XmlFixedRow) o;
        return values.equals(that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }

}
