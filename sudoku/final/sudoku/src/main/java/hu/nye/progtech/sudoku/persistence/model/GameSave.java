package hu.nye.progtech.sudoku.persistence.model;

import hu.nye.progtech.sudoku.model.RawMap;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "game_saves")
public class GameSave extends RawMap {
    public static final Integer DEFAULT_ID = 1;
    @Id
    private Integer id = DEFAULT_ID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GameSave gameSave = (GameSave) o;
        return id == gameSave.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
