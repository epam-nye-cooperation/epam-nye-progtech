package hu.nye.progtech.sudoku.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "gridLine")
public class GridLine {

    private String line;

    private int rowNumber;

    private int columnCount;

    private boolean modified;

    public GridLine() {
    }

    public GridLine(String line, int rowNumber, int columnCount, boolean modified) {
        this.line = line;
        this.rowNumber = rowNumber;
        this.columnCount = columnCount;
        this.modified = modified;
    }

    @XmlElement
    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    @XmlAttribute
    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    @XmlTransient
    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }
}
