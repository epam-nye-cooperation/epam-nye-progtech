package hu.nye.progtech.sudoku.persist.xml.model;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorOrder;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "grid")
public class GridXml {

    private String name;

    private int rowCount;

    private int columnCount;

    private boolean modified;

    private List<GridLineXml> lines;

    public GridXml() {
    }

    public GridXml(String name, int rowCount, int columnCount, boolean modified, List<GridLineXml> lines) {
        this.name = name;
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.modified = modified;
        this.lines = lines;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
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


    @XmlElementWrapper(name = "gridLines")
    @XmlElement(name = "line")
    public List<GridLineXml> getLines() {
        return lines;
    }

    public void setLines(List<GridLineXml> lines) {
        this.lines = lines;
    }
}
