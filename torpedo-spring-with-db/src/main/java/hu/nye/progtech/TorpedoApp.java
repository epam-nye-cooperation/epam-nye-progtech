package hu.nye.progtech;

import hu.nye.progtech.domain.Game;
import hu.nye.progtech.domain.GameMap;
import hu.nye.progtech.domain.Player;
import hu.nye.progtech.init.MapInit;
import hu.nye.progtech.init.PlayerInit;
import hu.nye.progtech.service.GameService;
import hu.nye.progtech.service.MapInitDeciderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TorpedoApp {

    public static void main(final String[] args) {
        final ApplicationContext context = SpringApplication.run(TorpedoApp.class, args);
        final MapInit mapInit = context.getBean(MapInitDeciderService.class).getMapInitInstance();
        final GameMap gameMap = mapInit.readMapDetails();
        final Player player = context.getBean(PlayerInit.class).readPlayerDetails();
        context.getBean(GameService.class).startGame(new Game(gameMap, player));
    }
}
