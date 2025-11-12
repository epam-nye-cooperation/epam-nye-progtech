package hu.nye.progtech.domain;

import lombok.Data;

@Data
public final class Game {

    private final GameMap gameMap;
    private final Player player;

    public Game(final GameMap gameMap, final Player player) {
        this.gameMap = gameMap;
        this.player = player;
    }
}
