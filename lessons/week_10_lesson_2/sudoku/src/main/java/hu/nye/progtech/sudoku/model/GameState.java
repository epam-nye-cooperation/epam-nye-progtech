package hu.nye.progtech.sudoku.model;

import java.util.Objects;

/**
 * Represents the current state of the game.
 */
public class GameState {

    public static GameStateBuilder builder() {
        return new GameStateBuilder();
    }

    private MapVO currentMap;
    private boolean shouldExit;

    public GameState(MapVO currentMap, boolean shouldExit) {
        this.currentMap = currentMap;
        this.shouldExit = shouldExit;
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
        return shouldExit == gameState.shouldExit && currentMap.equals(gameState.currentMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentMap, shouldExit);
    }

    @Override
    public String toString() {
        return "State{" +
            "currentMap=" + currentMap +
            ", shouldExit=" + shouldExit +
            '}';
    }

    /**
     * Builder for {@link GameState}.
     */
    public static final class GameStateBuilder {
        private MapVO currentMap;
        private boolean shouldExit;

        private GameStateBuilder() {
        }

        public static GameStateBuilder builder() {
            return new GameStateBuilder();
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
            return new GameState(currentMap, shouldExit);
        }
    }

}
