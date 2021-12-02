package hu.nye.progtech.sudoku.core.model;

import java.util.Objects;

/**
 * Represents the current state of the game.
 */
public class GameState {

    public static GameStateBuilder builder() {
        return new GameStateBuilder();
    }

    private String username;
    private MapVO currentMap;
    private boolean shouldExit;

    public GameState(String username, MapVO currentMap, boolean shouldExit) {
        this.username = username;
        this.currentMap = currentMap;
        this.shouldExit = shouldExit;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MapVO getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(MapVO currentMap) {
        this.currentMap = currentMap;
    }

    public boolean isShouldExit() {
        return shouldExit;
    }

    public void setShouldExit(boolean shouldExit) {
        this.shouldExit = shouldExit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameState gameState = (GameState) o;
        return shouldExit == gameState.shouldExit && Objects.equals(username, gameState.username)
                && Objects.equals(currentMap, gameState.currentMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, currentMap, shouldExit);
    }

    /**
     * Builder for {@link GameState}.
     */
    public static final class GameStateBuilder {
        private String username;
        private MapVO currentMap;
        private boolean shouldExit;

        private GameStateBuilder() {
        }

        public static GameStateBuilder builder() {
            return new GameStateBuilder();
        }

        public GameStateBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public GameStateBuilder withCurrentMap(MapVO currentMap) {
            this.currentMap = currentMap;
            return this;
        }

        public GameStateBuilder withShouldExit(boolean shouldExit) {
            this.shouldExit = shouldExit;
            return this;
        }

        public GameState build() {
            return new GameState(username, currentMap, shouldExit);
        }
    }

}
