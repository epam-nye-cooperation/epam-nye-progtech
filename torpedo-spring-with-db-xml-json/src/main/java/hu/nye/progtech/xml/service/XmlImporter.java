package hu.nye.progtech.xml.service;

import hu.nye.progtech.domain.*;
import hu.nye.progtech.xml.domain.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Service
public class XmlImporter {

    @SneakyThrows
    public Game importGame() {
        final JAXBContext jaxbContext = JAXBContext.newInstance(GameXml.class);
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        final GameXml gameXml = (GameXml) unmarshaller.unmarshal(new File("game-save.xml"));
        return convertXmlToGame(gameXml);
    }

    private Game convertXmlToGame(final GameXml gameXml) {
        return new Game(convertXmlToGameMap(gameXml.getGameMap()), convertXmlToPlayer(gameXml.getPlayer()));
    }

    private Player convertXmlToPlayer(final PlayerXml playerXml) {
        return new Player(playerXml.getName());
    }

    private GameMap convertXmlToGameMap(final GameMapXml gameMapXml) {
        return new GameMap(gameMapXml.getSize(), convertXmlToShip(gameMapXml.getShip()),
                convertXmlToRocketDestination(gameMapXml.getMissedTargets()));
    }

    private Ship convertXmlToShip(final ShipXml shipXml) {
        return new Ship(shipXml.getLength(), shipXml.getStartRow(), shipXml.getStartCol(),
                shipXml.isHorizontal(), shipXml.getHits());
    }

    private Set<RocketDestination> convertXmlToRocketDestination(final Set<RocketDestinationXml> rocketDestinationsXml) {
        final Set<RocketDestination> rocketDestinations = new HashSet<>();

        if(rocketDestinationsXml != null) {
            for(final RocketDestinationXml rocketDestinationXml : rocketDestinationsXml) {
                rocketDestinations.add(new RocketDestination(rocketDestinationXml.getRow(), rocketDestinationXml.getCol()));
            }
        }

        return rocketDestinations;
    }

}
