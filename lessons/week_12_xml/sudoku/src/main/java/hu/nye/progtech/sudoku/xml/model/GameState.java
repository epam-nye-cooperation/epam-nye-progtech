package hu.nye.progtech.sudoku.xml.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement( name = "gameState")
public class GameState {

    private int id;
    private List<Row> rows;

    public GameState(){}

    public GameState(int id, List<Row> rows) {
        this.id = id;
        this.rows = rows;
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "id=" + id +
                ", rows=" + rows +
                '}';
    }
}
