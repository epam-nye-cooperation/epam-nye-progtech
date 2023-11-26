package hu.nye.progtech.sudoku.persist.xml.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

public class GridLineXml {

    private int columnCount;

    private String line;

    private int rowNumber;

    public GridLineXml() {
    }

    public GridLineXml(int columnCount, String line, int rowNr) {
        this.columnCount = columnCount;
        this.line = line;
        this.rowNumber = rowNr;
    }

    @XmlAttribute
    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    @XmlElement
    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @XmlElement(name = "rowNumber")
    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }
}
