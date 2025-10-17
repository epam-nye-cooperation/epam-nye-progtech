package hu.nye.progtech.init;

import hu.nye.progtech.domain.Player;
import hu.nye.progtech.service.ConsoleService;

public class PlayerInit {
    private final ConsoleService consoleService;

    public PlayerInit(final ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    public Player readPlayerDetails() {
        return new Player(consoleService.readStringFromConsole("Please provide your name: "));
    }
}
