package hu.nye.progtech.sudoku.persistence.xml.model;

import java.util.List;
import java.util.Objects;

import hu.nye.progtech.sudoku.core.model.MapVO;
import jakarta.xml.bind.annotation.XmlElement;

/**
 * XML representation of a row of the map state from a {@link MapVO} object.
 */
public class XmlMapRow {

    private List<Integer> values;

    public XmlMapRow() {
    }

    public XmlMapRow(List<Integer> values) {
        this.values = values;
    }

    @XmlElement(name = "value")
    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
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
        XmlMapRow xmlMapRow = (XmlMapRow) o;
        return values.equals(xmlMapRow.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }

}
