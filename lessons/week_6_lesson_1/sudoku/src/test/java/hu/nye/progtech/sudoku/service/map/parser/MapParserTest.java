package hu.nye.progtech.sudoku.service.map.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.service.exception.MapParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MapParserTest {

    private static final int NUMBER_OF_ROWS = 2;
    private static final int NUMBER_OF_COLUMNS = 2;

    private static final List<String> RAW_MAP = List.of(
        "01",
        "20"
    );

    private static final List<String> MAP_WITH_FEWER_ROWS = List.of(
        "01"
    );

    private static final int[][] MAP = {
        {0, 1},
        {2, 0}
    };

    private static final boolean[][] FIXED = {
        {false, true},
        {true, false}
    };

    private static final MapVO EXPECTED_MAP = new MapVO(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, MAP, FIXED);

    private MapParser underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MapParser(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);
    }

    @Test
    public void testParseMapShouldReturnValidMapVoRepresentation() throws MapParseException {
        // given in setup

        // when
        MapVO result = underTest.parseMap(RAW_MAP);

        // then
        assertEquals(EXPECTED_MAP, result);
    }

}
