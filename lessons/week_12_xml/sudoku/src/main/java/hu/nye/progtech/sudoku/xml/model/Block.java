package hu.nye.progtech.sudoku.xml.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Block {

    private int id;
    private int value;
    private boolean isFixed;

    public Block() {}

    public Block(int id, int value, boolean isFixed) {
        this.id = id;
        this.value = value;
        this.isFixed = isFixed;
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @XmlAttribute
    public boolean isFixed() {
        return isFixed;
    }

    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }

    @Override
    public String toString() {
        return "Block{" +
                "id=" + id +
                ", value=" + value +
                ", isFixed=" + isFixed +
                '}';
    }
}
