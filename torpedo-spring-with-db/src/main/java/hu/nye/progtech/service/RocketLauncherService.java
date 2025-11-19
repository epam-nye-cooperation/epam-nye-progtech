package hu.nye.progtech.service;

import hu.nye.progtech.domain.GameMap;
import hu.nye.progtech.domain.RocketDestination;
import hu.nye.progtech.domain.Ship;
import org.springframework.stereotype.Service;

@Service
public class RocketLauncherService {
    private final ConsoleService consoleService;

    public RocketLauncherService(final ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    public RocketDestination getRocketDestination() {
        final int row = consoleService.readIntFromConsole("Please provide the rocket's destination row:");
        final int col = consoleService.readIntFromConsole("Please provide the rocket's destination col:");
        return new RocketDestination(row, col);
    }

    public boolean isRocketHit(final GameMap gameMap, final RocketDestination rocketDestination) {
        final boolean isHit;
        final Ship ship = gameMap.getShip();

        if (ship.isHorizontal()) {
            isHit = checkHorizontalHit(ship, rocketDestination);
        } else {
            isHit = checkVerticalHit(ship, rocketDestination);
        }

        if (!isHit) {
            gameMap.getMissedTargets().add(rocketDestination);
        }

        return isHit;
    }

    private boolean checkHorizontalHit(final Ship ship, final RocketDestination rocketDestination) {
        boolean isHorizontalHit = false;
        if (ship.getStartRow() == rocketDestination.getRow()) {
            for (int i = 0; i < ship.getLength(); i++) {
                if (ship.getStartCol() + i == rocketDestination.getCol()) {
                    ship.getHits()[i] = true;
                    isHorizontalHit = true;
                }
            }
        }

        return isHorizontalHit;
    }

    private boolean checkVerticalHit(final Ship ship, final RocketDestination rocketDestination) {
        boolean isVerticalHit = false;
        if (ship.getStartCol() == rocketDestination.getCol()) {
            for (int i = 0; i < ship.getLength(); i++) {
                if (ship.getStartRow() + i == rocketDestination.getRow()) {
                    ship.getHits()[i] = true;
                    isVerticalHit = true;
                }
            }
        }

        return isVerticalHit;
    }
}
