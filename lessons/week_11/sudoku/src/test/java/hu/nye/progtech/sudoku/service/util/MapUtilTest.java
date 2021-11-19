package hu.nye.progtech.sudoku.service.util;

import hu.nye.progtech.sudoku.model.BoxDescription;
import hu.nye.progtech.sudoku.model.MapVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link MapUtil}.
 */
public class MapUtilTest {

    private static final int NUMBER_OF_ROWS = 3;
    private static final int NUMBER_OF_COLUMNS = 3;
    private static final int[][] MAP = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    private static final int[][] NOT_COMPLETED_MAP = {
        {0, 0, 0},
        {4, 5, 6},
        {7, 8, 9}
    };
    private static final MapVO MAP_VO = new MapVO(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, MAP, null);
    private static final MapVO NOT_COMPLETED_MAP_VO = new MapVO(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, NOT_COMPLETED_MAP, null);

    private static final int FIRST_ROW_INDEX = 0;
    private static final int FIRST_COLUMN_INDEX = 0;
    private static final BoxDescription TOP_LEFT_TWO_BY_TWO_BOX = new BoxDescription(0, 2, 0, 2);

    private static final List<Integer> FIRST_ROW_AS_LIST = List.of(1, 2, 3);
    private static final List<Integer> FIRST_COLUMN_AS_LIST = List.of(1, 4, 7);
    private static final List<Integer> TOP_LEFT_TWO_BY_TWO_BOX_AS_LIST = List.of(1, 2, 4, 5);

    private MapUtil underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MapUtil();
    }

    @Test
    public void testGetRowOfMapShouldReturnTheSelectedRowAsList() {
        // given in setup

        // when
        List<Integer> result = underTest.getRowOfMap(MAP_VO, FIRST_ROW_INDEX);

        // then
        assertEquals(FIRST_ROW_AS_LIST, result);
    }

    @Test
    public void testGetColumnOfMapShouldReturnTheSelectedColumnAsList() {
        // given in setup

        // when
        List<Integer> result = underTest.getColumnOfMap(MAP_VO, FIRST_COLUMN_INDEX);

        // then
        assertEquals(FIRST_COLUMN_AS_LIST, result);
    }

    @Test
    public void testGetBoxValuesOfMapShouldReturnTheSelectedBoxAsList() {
        // given in setup

        // when
        List<Integer> result = underTest.getBoxValuesOfMap(MAP_VO, TOP_LEFT_TWO_BY_TWO_BOX);

        // then
        assertEquals(TOP_LEFT_TWO_BY_TWO_BOX_AS_LIST, result);
    }

    @Test
    public void testIsMapCompletedShouldReturnTrueWhenThereAreNoZeroValuesInTheMap() {
        // given in setup

        // when
        boolean result = underTest.isMapCompleted(MAP_VO);

        // then
        assertTrue(result);
    }

    @Test
    public void testIsMapCompletedShouldReturnFalseWhenThereAreStillZeroValuesInTheMap() {
        // given in setup

        // when
        boolean result = underTest.isMapCompleted(NOT_COMPLETED_MAP_VO);

        // then
        assertFalse(result);
    }

}
