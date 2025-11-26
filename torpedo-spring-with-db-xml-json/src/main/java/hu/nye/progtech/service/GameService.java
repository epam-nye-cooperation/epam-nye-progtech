package hu.nye.progtech.service;

import hu.nye.progtech.database.entity.HighScore;
import hu.nye.progtech.display.MapDisplayer;
import hu.nye.progtech.domain.Game;
import hu.nye.progtech.domain.GameMap;
import hu.nye.progtech.domain.Player;
import hu.nye.progtech.domain.RocketDestination;
import hu.nye.progtech.domain.Ship;
import hu.nye.progtech.xml.service.XmlExporter;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameStateDeciderService deciderService;
    private final MapDisplayer mapDisplayer;
    private final RocketLauncherService launcherService;
    private final ConsoleService consoleService;
    private final HighScoreService highScoreService;
    private final XmlExporter xmlExporter;

    public GameService(final GameStateDeciderService deciderService, final MapDisplayer mapDisplayer,
                       final RocketLauncherService launcherService, final ConsoleService consoleService,
                       final HighScoreService highScoreService, final XmlExporter xmlExporter) {
        this.deciderService = deciderService;
        this.mapDisplayer = mapDisplayer;
        this.launcherService = launcherService;
        this.consoleService = consoleService;
        this.highScoreService = highScoreService;
        this.xmlExporter = xmlExporter;
    }

    public void startGame(final Game game) {
        final Player player = game.getPlayer();
        final GameMap gameMap = game.getGameMap();
        final Ship ship = gameMap.getShip();
        final HighScore highScore = highScoreService.findByPlayerNameOrCreate(player.getName());
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
            xmlExporter.export(game);
        }

        highScore.setGamesWon(highScore.getGamesWon() + 1);
        highScoreService.save(highScore);

        consoleService.printWithPlayerName("Congratulation {}, there is no more ship on the map! ", player.getName());
        mapDisplayer.displayMap(gameMap);
    }
}
