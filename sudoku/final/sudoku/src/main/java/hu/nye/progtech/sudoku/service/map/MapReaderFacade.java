package hu.nye.progtech.sudoku.service.map;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.model.RawMap;
import hu.nye.progtech.sudoku.service.exception.GameException;
import hu.nye.progtech.sudoku.service.exception.MapSavingException;
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
        RawMap rawMap = mapReader.readMap();
        MapVO mapVO = mapParser.parseMap(rawMap);
        mapValidator.validate(mapVO);

        return mapVO;
    }

}
