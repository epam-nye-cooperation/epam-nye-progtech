package hu.nye.progtech.sudoku.service.map;

import java.util.List;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.service.exception.MapParsingException;
import hu.nye.progtech.sudoku.service.exception.MapReadingException;
import hu.nye.progtech.sudoku.service.exception.MapValidationException;
import hu.nye.progtech.sudoku.service.map.parser.MapParser;
import hu.nye.progtech.sudoku.service.map.reader.MapReader;
import hu.nye.progtech.sudoku.service.validator.MapValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Facade that makes it easier to read a map.
 */
public class MapReaderFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapReaderFacade.class);

    private final MapReader mapReader;
    private final MapParser mapParser;
    private final MapValidator mapValidator;

    public MapReaderFacade(MapReader mapReader, MapParser mapParser, MapValidator mapValidator) {
        this.mapReader = mapReader;
        this.mapParser = mapParser;
        this.mapValidator = mapValidator;
    }

    /**
     * Reads a map.
     *
     * This method hides the low level operations of reading a map, like reading it
     * in a raw format, parsing the map then also running a validation on it.
     *
     * @return a parsed map as a {@link MapVO} object
     */
    public MapVO readMap() {
        try {
            List<String> rows = mapReader.readMap();
            MapVO mapVO = mapParser.parseMap(rows);
            mapValidator.validate(mapVO);

            return mapVO;
        } catch (MapReadingException e) {
            LOGGER.error("Failed to read map", e);
            throw new RuntimeException("Failed to read map");
        } catch (MapParsingException e) {
            LOGGER.error("Failed to parse map", e);
            throw new RuntimeException("Failed to parse map");
        } catch (MapValidationException e) {
            LOGGER.error("Failed to validate map", e);
            throw new RuntimeException("The loaded map was invalid");
        }
    }

}
