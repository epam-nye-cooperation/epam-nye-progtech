package hu.nye.progtech.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public final class Game {

    private GameMap gameMap;
    private Player player;

    public Game(final GameMap gameMap, final Player player) {
        this.gameMap = gameMap;
        this.player = player;
    }
}
