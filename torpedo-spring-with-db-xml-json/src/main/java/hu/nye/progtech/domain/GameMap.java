package hu.nye.progtech.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class GameMap {

    private int size;
    private Ship ship;
    private Set<RocketDestination> missedTargets;

    public GameMap(final int size, final Ship ship) {
        this.size = size;
        this.ship = ship;
        this.missedTargets = new HashSet<>();
    }
}
