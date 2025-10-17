package hu.nye.progtech.service;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleService.class);
    private final Scanner scanner;

    public ConsoleService(final Scanner scanner) {
        this.scanner = scanner;
    }

    public int readIntFromConsole(final String message) {
        LOGGER.info(message);
        return scanner.nextInt();
    }

    public boolean readBooleanFromConsole(final String message) {
        LOGGER.info(message);
        return scanner.nextBoolean();
    }

    public String readStringFromConsole(final String message) {
        LOGGER.info(message);
        return scanner.next();
    }

    public void print(final String message) {
        LOGGER.info(message);
    }

    public void printWithPlayerName(final String message, final String playerName) {
        LOGGER.info(message, playerName);
    }
}
