package hu.nye.progtech.service;

import hu.nye.progtech.display.MapDisplayer;
import hu.nye.progtech.domain.Game;
import hu.nye.progtech.domain.GameMap;
import hu.nye.progtech.domain.Player;
import hu.nye.progtech.domain.Ship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("PMD.GuardLogStatement")
public class GameService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);
    private final GameStateDeciderService deciderService;
    private final MapDisplayer mapDisplayer;

    public GameService(final GameStateDeciderService deciderService, final MapDisplayer mapDisplayer) {
        this.deciderService = deciderService;
        this.mapDisplayer = mapDisplayer;
    }

    public void startGame(final Game game) {
        int counter = 0;
        final Player player = game.getPlayer();
        final GameMap gameMap = game.getMap();
        final Ship ship = gameMap.getShip();
        LOGGER.info("Hi {}, the game has been started, this is your map: ", player.getName());

        while (!deciderService.isFinished(ship)) {
            // Kiíratjuk a map-at
            mapDisplayer.displayMap(gameMap);
            ship.getHits()[counter] = true;
            // Kérünk egy destination-t
            // lövünk
            counter++;
        }

        LOGGER.info("Congratulation {}, there is no more ship on the map! ", player.getName());
        mapDisplayer.displayMap(gameMap);
    }
}
