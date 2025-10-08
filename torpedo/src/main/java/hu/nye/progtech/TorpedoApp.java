package hu.nye.progtech;

import java.util.Scanner;

import hu.nye.progtech.display.MapDisplayer;
import hu.nye.progtech.domain.Game;
import hu.nye.progtech.domain.GameMap;
import hu.nye.progtech.domain.Player;
import hu.nye.progtech.init.MapInit;
import hu.nye.progtech.init.PlayerInit;
import hu.nye.progtech.service.GameService;
import hu.nye.progtech.service.GameStateDeciderService;

public class TorpedoApp {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final MapInit mapInit = new MapInit(scanner);
        final PlayerInit playerInit = new PlayerInit(scanner);

        final GameMap gameMap = mapInit.readMapDetails();
        final Player player = playerInit.readPlayerDetails();
        final GameStateDeciderService deciderService = new GameStateDeciderService();
        final MapDisplayer mapDisplayer = new MapDisplayer();
        final GameService gameService = new GameService(deciderService, mapDisplayer);
        gameService.startGame(new Game(gameMap, player));
    }
}
