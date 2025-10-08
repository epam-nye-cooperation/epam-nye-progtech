package hu.nye.progtech.init;

import java.util.Scanner;

import hu.nye.progtech.domain.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerInit {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerInit.class);
    private final Scanner scanner;

    public PlayerInit(final Scanner scanner) {
        this.scanner = scanner;
    }

    public Player readPlayerDetails() {
        LOGGER.info("Please provide your name: ");
        final String name = scanner.next();
        return new Player(name);
    }
}
