package hu.nye.progtech.sudoku;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import hu.nye.progtech.sudoku.model.BoxDescription;
import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.service.exception.MapParseException;
import hu.nye.progtech.sudoku.service.exception.MapReadException;
import hu.nye.progtech.sudoku.service.exception.MapValidationException;
import hu.nye.progtech.sudoku.service.map.parser.MapParser;
import hu.nye.progtech.sudoku.service.map.reader.MapReader;
import hu.nye.progtech.sudoku.service.map.reader.impl.BufferedReaderMapReader;
import hu.nye.progtech.sudoku.service.map.validator.MapValidator;
import hu.nye.progtech.sudoku.service.map.validator.impl.MapByBoxValidator;
import hu.nye.progtech.sudoku.service.map.validator.impl.MapByColumnValidator;
import hu.nye.progtech.sudoku.service.map.validator.impl.MapByRowValidator;
import hu.nye.progtech.sudoku.service.util.CollectionUtil;
import hu.nye.progtech.sudoku.service.util.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        int[][] map = {
            {0, 1},
            {2, 3}
        };
        boolean[][] fixed = {
            {false, true},
            {true, true}
        };
        MapVO mapVO = new MapVO(2, 2, map, fixed);

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("MapVO: " + mapVO);
        }

        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("map/beginner.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        MapReader mapReader = new BufferedReaderMapReader(bufferedReader);
        try {
            List<String> strings = mapReader.readMap();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("MapReader readMap output: " + strings);
            }

            MapParser mapParser = new MapParser(9, 9);
            MapVO mapVO1 = mapParser.parseMap(strings);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("MapParser parseMap output: " + mapVO1);
            }

            CollectionUtil collectionUtil = new CollectionUtil();
            MapUtil mapUtil = new MapUtil();

            MapValidator mapByRowValidator = new MapByRowValidator(collectionUtil, mapUtil);
            MapValidator mapByColumnValidator = new MapByColumnValidator(collectionUtil, mapUtil);
            MapValidator mapByBoxValidator = new MapByBoxValidator(collectionUtil, mapUtil, BoxDescription.BOX_DESCRIPTION_LIST);

            mapByRowValidator.validate(mapVO1);
            mapByColumnValidator.validate(mapVO1);
            mapByBoxValidator.validate(mapVO1);
        } catch (MapReadException e) {
            LOGGER.error("MapReadException was thrown in Main", e);
        } catch (MapParseException e) {
            LOGGER.error("MapParseException was thrown in Main", e);
        } catch (MapValidationException e) {
            LOGGER.error("MapValidationException was thrown in Main", e);
        }

    }

}
