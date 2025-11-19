package hu.nye.progtech.service;


import hu.nye.progtech.domain.Ship;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameStateDeciderServiceTest {

    private final GameStateDeciderService underTest = new GameStateDeciderService();

    @Test
    public void isFinishedShouldReturnTrue() {
        // GIVEN
        final Ship ship = new Ship(3, 0, 0, true);
        Arrays.fill(ship.getHits(), true);

        // WHEN
        boolean isFinished = underTest.isFinished(ship);

        // THEN
        assertTrue(isFinished);
    }

    @Test
    public void isFinishedShouldReturnFalse() {
        // GIVEN
        final Ship ship = new Ship(3, 0, 0, true);

        // WHEN
        boolean isFinished = underTest.isFinished(ship);

        // THEN
        assertFalse(isFinished);
    }
}
