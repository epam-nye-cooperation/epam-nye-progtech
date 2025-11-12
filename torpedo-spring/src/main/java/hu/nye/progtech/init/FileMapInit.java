package hu.nye.progtech.init;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import hu.nye.progtech.domain.GameMap;
import hu.nye.progtech.domain.Ship;
import org.springframework.stereotype.Component;

@Component
public class FileMapInit implements MapInit {
    private static final String FILE_NAME = "map.txt";

    @Override
    public GameMap readMapDetails() {
        final GameMap gameMap;

        try {
            final List<String> lines = Files.readAllLines(Paths.get(ClassLoader.getSystemResource(FILE_NAME).toURI()));
            final int mapSize = Integer.parseInt(lines.get(0));
            final int shipSize = Integer.parseInt(lines.get(1));
            final int shipStartRow = Integer.parseInt(lines.get(2));
            final int shipStartCol = Integer.parseInt(lines.get(3));
            final boolean isShipHorizontal = Boolean.parseBoolean(lines.get(4));
            final Ship ship = new Ship(shipSize, shipStartRow, shipStartCol, isShipHorizontal);
            gameMap = new GameMap(mapSize, ship);
        } catch (Exception e) {
          throw new RuntimeException("Error during reading the file.", e);
        }

        return gameMap;
    }
}
