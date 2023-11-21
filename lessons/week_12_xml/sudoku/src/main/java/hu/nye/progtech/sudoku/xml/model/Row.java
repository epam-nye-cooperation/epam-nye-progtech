package hu.nye.progtech.sudoku.xml.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement
public class Row {

    private int id;
    private List<Block> blocks;

    public Row() {}

    public Row(int id, List<Block> blocks) {
        this.id = id;
        this.blocks = blocks;
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public String toString() {
        return "Row{" +
                "id=" + id +
                ", blocks=" + blocks +
                '}';
    }
}
