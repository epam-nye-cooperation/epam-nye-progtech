package hu.nye.progtech;

import java.util.Scanner;

import hu.nye.progtech.display.MapDisplayer;
import hu.nye.progtech.domain.Game;
import hu.nye.progtech.domain.GameMap;
import hu.nye.progtech.domain.Player;
import hu.nye.progtech.init.MapInit;
import hu.nye.progtech.init.PlayerInit;
import hu.nye.progtech.service.ConsoleService;
import hu.nye.progtech.service.GameService;
import hu.nye.progtech.service.GameStateDeciderService;
import hu.nye.progtech.service.MapInitDeciderService;
import hu.nye.progtech.service.RocketLauncherService;

public class TorpedoApp {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final ConsoleService consoleService = new ConsoleService(scanner);
        final MapInitDeciderService mapInitDecider = new MapInitDeciderService(consoleService);
        final MapInit mapInit = mapInitDecider.getMapInitInstance();
        final PlayerInit playerInit = new PlayerInit(consoleService);

        final GameMap gameMap = mapInit.readMapDetails();
        final Player player = playerInit.readPlayerDetails();
        final GameStateDeciderService deciderService = new GameStateDeciderService();
        final MapDisplayer mapDisplayer = new MapDisplayer(consoleService);
        final RocketLauncherService launcherService = new RocketLauncherService(consoleService);
        final GameService gameService = new GameService(deciderService, mapDisplayer, launcherService, consoleService);
        gameService.startGame(new Game(gameMap, player));
        scanner.close();
    }
}
