package hu.nye.progtech.sudoku.model;

public class GameStateModel {
    private int id;
    private String map;
    private int playerId;

    public GameStateModel(int id, String map, int playerId) {
        this.id = id;
        this.map = map;
        this.playerId = playerId;
    }

    public GameStateModel() {}

    @Override
    public String toString() {
        return "GameStateModel{" +
                "id=" + id +
                ", map='" + map + '\'' +
                ", playerId=" + playerId +
                '}';
    }
}
