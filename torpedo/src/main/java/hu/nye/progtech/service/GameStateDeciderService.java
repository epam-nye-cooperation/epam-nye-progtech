package hu.nye.progtech.service;

import hu.nye.progtech.domain.Ship;

public class GameStateDeciderService {

    public boolean isFinished(final Ship ship) {
        boolean isFinished = true;

        for (final boolean hit : ship.getHits()) {
            if (!hit) {
                isFinished = false;
                break;
            }
        }

        return isFinished;
    }
}
