package hu.nye.progtech;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.nye.progtech.domain.Game;
import hu.nye.progtech.domain.GameMap;
import hu.nye.progtech.domain.Player;
import hu.nye.progtech.init.MapInit;
import hu.nye.progtech.init.PlayerInit;
import hu.nye.progtech.service.GameService;
import hu.nye.progtech.service.MapInitDeciderService;
import hu.nye.progtech.xml.service.XmlImporter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TorpedoApp {

    public static void main(final String[] args) throws JsonProcessingException {
        final ApplicationContext context = SpringApplication.run(TorpedoApp.class, args);
        /*final MapInit mapInit = context.getBean(MapInitDeciderService.class).getMapInitInstance();
        final GameMap gameMap = mapInit.readMapDetails();
        final Player player = context.getBean(PlayerInit.class).readPlayerDetails();*/
        final XmlImporter xmlImporter = context.getBean(XmlImporter.class);
        final Game game =  xmlImporter.importGame();

        final ObjectMapper objectMapper = new ObjectMapper();
        final String jsonString = objectMapper.writeValueAsString(game);
        System.out.println(jsonString);
        final Game gameFromJson = objectMapper.readValue(jsonString, Game.class);
        System.out.println(gameFromJson);
        context.getBean(GameService.class).startGame(game);
    }
}
