package hu.nye.progtech;

public class Player {
    private int id;
    private String name;
    private String password;
    private int gameState;


    public Player(int id, String name, String password, int gameState) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gameState = gameState;
    }


    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gameState=" + gameState +
                '}';
    }
}
