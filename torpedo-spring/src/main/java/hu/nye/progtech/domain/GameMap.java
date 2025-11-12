package hu.nye.progtech.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public final class GameMap {

    private final int size;
    private final Ship ship;
    private final Set<RocketDestination> missedTargets;

    public GameMap(final int size, final Ship ship) {
        this.size = size;
        this.ship = ship;
        this.missedTargets = new HashSet<>();
    }
}
