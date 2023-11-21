package hu.nye.progtech.sudoku.service.map.reader.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public List<String> readMap() throws MapReadingException {
        LOGGER.info("Reading the map");

        String row;
        List<String> rows = new ArrayList<>();

        try {
            while ((row = reader.readLine()) != null) {
                rows.add(row);
            }
        } catch (IOException e) {
            LOGGER.error("Failed to read map", e);
            throw new MapReadingException("Failed to read map");
        }

        return rows;
    }

}
