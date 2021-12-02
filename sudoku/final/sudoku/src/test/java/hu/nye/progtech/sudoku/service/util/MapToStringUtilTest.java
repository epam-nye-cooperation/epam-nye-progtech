package hu.nye.progtech.sudoku.service.util;

import hu.nye.progtech.sudoku.model.MapVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapToStringUtilTest {

    private static final int[][] MAP = new int[][] {
            {1,0},
            {2,3}
    };

    private static final boolean[][] FIXED = new boolean[][] {
            {true, false},
            {true, true}
    };

    private MapToStringUtil underTest = new MapToStringUtil();

    @Test
    public void testConvertMapVoMapToStringShouldReturnWithCorrectStringRepresentation() {
        // Given
        MapVO mapVO = new MapVO(2, 2, MAP, FIXED);
        String expected = "10\n23\n";

        // When
        String actual = underTest.convertMapVoMapToString(mapVO);

        // Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testConvertMapVoFixedToStringShouldReturnWithCorrectStringRepresentation() {
        // Given
        MapVO mapVO = new MapVO(2, 2, MAP, FIXED);
        String expected = "10\n11\n";

        // When
        String actual = underTest.convertMapVoFixedToString(mapVO);

        // Then
        Assertions.assertEquals(expected, actual);
    }

}
