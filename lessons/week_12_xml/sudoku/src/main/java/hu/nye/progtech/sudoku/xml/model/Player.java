package hu.nye.progtech.sudoku.xml.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import hu.nye.progtech.sudoku.xml.adapter.LocalDateTimeAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDateTime;

@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
public class Player {
    private int id;
    private String name;
    private String password;
    private GameState gameState;
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @XmlElement
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registrationDate;

    public Player() {}

    public Player(int id, String name, String password, GameState gameState) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gameState = gameState;
        this.registrationDate = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gameState=" + gameState +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
