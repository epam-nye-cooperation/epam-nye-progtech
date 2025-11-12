package hu.nye.progtech.service;

import hu.nye.progtech.display.MapDisplayer;
import hu.nye.progtech.domain.Game;
import hu.nye.progtech.domain.GameMap;
import hu.nye.progtech.domain.Player;
import hu.nye.progtech.domain.RocketDestination;
import hu.nye.progtech.domain.Ship;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameStateDeciderService deciderService;
    private final MapDisplayer mapDisplayer;
    private final RocketLauncherService launcherService;
    private final ConsoleService consoleService;

    public GameService(final GameStateDeciderService deciderService, final MapDisplayer mapDisplayer,
                       final RocketLauncherService launcherService, final ConsoleService consoleService) {
        this.deciderService = deciderService;
        this.mapDisplayer = mapDisplayer;
        this.launcherService = launcherService;
        this.consoleService = consoleService;
    }

    public void startGame(final Game game) {
        final Player player = game.getPlayer();
        final GameMap gameMap = game.getGameMap();
        final Ship ship = gameMap.getShip();
        consoleService.printWithPlayerName("Hi {}, the game has been started, this is your map: ", player.getName());

        while (!deciderService.isFinished(ship)) {
            mapDisplayer.displayMap(gameMap);
            final RocketDestination rocketDestination = launcherService.getRocketDestination();
            final boolean isHit = launcherService.isRocketHit(gameMap, rocketDestination);

            if (isHit) {
                consoleService.print("HIT!");
            } else {
                consoleService.print("MISSED!");
            }
        }
        consoleService.printWithPlayerName("Congratulation {}, there is no more ship on the map! ", player.getName());
        mapDisplayer.displayMap(gameMap);
    }
}
