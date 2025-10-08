package hu.nye.progtech.domain;

public final class GameMap {

    private final int size;
    private final Ship ship;

    public GameMap(final int size, final Ship ship) {
        this.size = size;
        this.ship = ship;
    }

    public int getSize() {
        return size;
    }

    public Ship getShip() {
        return ship;
    }
}
