package hu.nye.progtech.domain;

import java.util.HashSet;
import java.util.Set;

public final class GameMap {

    private final int size;
    private final Ship ship;
    private final Set<RocketDestination> missedTargets;

    public GameMap(final int size, final Ship ship) {
        this.size = size;
        this.ship = ship;
        this.missedTargets = new HashSet<>();
    }

    public int getSize() {
        return size;
    }

    public Ship getShip() {
        return ship;
    }

    public Set<RocketDestination> getMissedTargets() {
        return missedTargets;
    }
}
