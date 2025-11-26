package hu.nye.progtech.xml.service;

import hu.nye.progtech.domain.*;
import hu.nye.progtech.xml.domain.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Service
public class XmlExporter {

    @SneakyThrows
    public void export(final Game game) {
        final GameXml gameXml = convertXmlDomainForGame(game);
        final JAXBContext jaxbContext = JAXBContext.newInstance(GameXml.class);
        final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(gameXml, new File("game-save.xml"));
    }

    private GameXml convertXmlDomainForGame(final Game game) {
        return new GameXml(convertXmlForGameMap(game.getGameMap()), convertXmlForPlayer(game.getPlayer()));
    }

    private PlayerXml convertXmlForPlayer(final Player player) {
        return new PlayerXml(player.getName());
    }

    private GameMapXml convertXmlForGameMap(final GameMap gameMap) {
        return new GameMapXml(gameMap.getSize(), convertXmlForShip(gameMap.getShip()),
                convertXmlForRocketDestination(gameMap.getMissedTargets()));
    }

    private ShipXml convertXmlForShip(final Ship ship) {
        return new ShipXml(ship.getLength(), ship.getStartRow(), ship.getStartCol(),
                ship.isHorizontal(), ship.getHits());
    }

    private Set<RocketDestinationXml> convertXmlForRocketDestination(final Set<RocketDestination> rocketDestinations) {
        final Set<RocketDestinationXml> rocketDestinationXmls = new HashSet<>();

        for(final RocketDestination rocketDestination : rocketDestinations) {
            rocketDestinationXmls.add(new RocketDestinationXml(rocketDestination.getRow(), rocketDestination.getCol()));
        }
        return rocketDestinationXmls;
    }
}
