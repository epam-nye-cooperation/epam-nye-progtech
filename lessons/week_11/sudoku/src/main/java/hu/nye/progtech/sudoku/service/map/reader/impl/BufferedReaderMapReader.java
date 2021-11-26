package hu.nye.progtech.sudoku.service.map.reader.impl;

import java.io.BufferedReader;
import java.io.IOException;

import hu.nye.progtech.sudoku.model.RawMap;
import hu.nye.progtech.sudoku.service.exception.MapReadingException;
import hu.nye.progtech.sudoku.service.map.reader.MapReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link MapReader} implementation that reads the raw representation of
 * a map from a {@link BufferedReader} source.
 */
public class BufferedReaderMapReader implements MapReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(BufferedReaderMapReader.class);

    private final BufferedReader reader;

    public BufferedReaderMapReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public RawMap readMap() throws MapReadingException {
        LOGGER.info("Reading the map");

        String row;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            while ((row = reader.readLine()) != null) {
                stringBuilder.append(row);
                stringBuilder.append("\n");
            }
        } catch (IOException e) {
            LOGGER.error("Failed to read map", e);
            throw new MapReadingException("Failed to read map");
        }

        String map = stringBuilder.toString();
        String fixed = mapToFixed(map);

        return new RawMap(map, fixed);
    }

    private String mapToFixed(String map) {
        StringBuilder stringBuilder = new StringBuilder();

        String[] characters = map.split("");
        for (String character : characters) {
            switch (character) {
                case "\n":
                    stringBuilder.append("\n");
                    break;
                case "0":
                    stringBuilder.append("0");
                    break;
                default:
                    stringBuilder.append("1");
            }
        }

        return stringBuilder.toString();
    }

}
