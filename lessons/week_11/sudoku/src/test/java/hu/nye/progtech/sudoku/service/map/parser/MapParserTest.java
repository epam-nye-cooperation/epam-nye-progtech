package hu.nye.progtech.sudoku.service.map.parser;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.model.RawMap;
import hu.nye.progtech.sudoku.service.exception.MapParsingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link MapParser}.
 */
public class MapParserTest {

    private static final int NUMBER_OF_ROWS = 2;
    private static final int NUMBER_OF_COLUMNS = 2;

    private static final String VALID_RAW_MAP = "01\n20\n";
    private static final String VALID_RAW_FIXED = "01\n10\n";
    private static final String INVALID_RAW_MAP_FEW_ROWS = "01\n";
    private static final String INVALID_RAW_FIXED_FEW_ROWS = "01\n";
    private static final String INVALID_RAW_MAP_FEW_COLUMNS = "01\n2\n";
    private static final String INVALID_RAW_FIXED_FEW_COLUMNS = "01\n1\n";
    private static final String INVALID_RAW_MAP_INVALID_CHARACTERS = "01\nab\n";
    private static final String INVALID_RAW_FIXED_INVALID_CHARACTERS = "01\n11\n";

    private static final int[][] MAP = {
        {0, 1},
        {2, 0}
    };

    private static final boolean[][] FIXED = {
        {false, true},
        {true, false}
    };

    private static final MapVO EXPECTED_MAP_VO = new MapVO(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, MAP, FIXED);

    private MapParser underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MapParser(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);
    }

    @Test
    public void testParseMapShouldReturnNewParsedMap() throws MapParsingException {
        // given
        RawMap input = new RawMap(VALID_RAW_MAP, VALID_RAW_FIXED);

        // when
        MapVO result = underTest.parseMap(input);

        // then
        assertEquals(EXPECTED_MAP_VO, result);
    }

    @Test
    public void testParseMapShouldThrowMapParsingExceptionWhenThereAreNotEnoughRows() throws MapParsingException {
        // given in setup
        RawMap input = new RawMap(INVALID_RAW_MAP_FEW_ROWS, INVALID_RAW_FIXED_FEW_ROWS);

        // when - then
        assertThrows(MapParsingException.class, () -> {
            underTest.parseMap(input);
        });
    }

    @Test
    public void testParseMapShouldThrowMapParsingExceptionWhenThereAreNotEnoughColumns() {
        // given in setup
        RawMap input = new RawMap(INVALID_RAW_MAP_FEW_COLUMNS, INVALID_RAW_FIXED_FEW_COLUMNS);

        // when - then
        assertThrows(MapParsingException.class, () -> {
            underTest.parseMap(input);
        });
    }

    @Test
    public void testParseMapShouldThrowMapParsingExceptionWhenThereAreInvalidCharacters() {
        // given in setup
        RawMap input = new RawMap(INVALID_RAW_MAP_INVALID_CHARACTERS, INVALID_RAW_FIXED_INVALID_CHARACTERS);

        // when - then
        assertThrows(MapParsingException.class, () -> {
            underTest.parseMap(input);
        });
    }

}
