package hu.nye.progtech.domain;

@SuppressWarnings("PMD.ShortClassName")
public final class Game {

    private final GameMap gameMap;
    private final Player player;

    public Game(final GameMap gameMap, final Player player) {
        this.gameMap = gameMap;
        this.player = player;
    }

    public GameMap getMap() {
        return gameMap;
    }

    public Player getPlayer() {
        return player;
    }
}
