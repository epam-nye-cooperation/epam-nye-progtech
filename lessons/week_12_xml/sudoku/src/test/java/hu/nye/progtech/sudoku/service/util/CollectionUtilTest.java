package hu.nye.progtech.sudoku.service.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link CollectionUtil}.
 */
public class CollectionUtilTest {

    private static final List<Integer> LIST_OF_NUMBERS = List.of(1, 0, 2, 0, 3);
    private static final List<Integer> LIST_OF_NON_ZERO_VALUES = List.of(1, 2, 3);

    private static final List<Integer> LIST_OF_DISTINCT_NUMBERS = List.of(1, 2, 3);
    private static final List<Integer> LIST_OF_NON_DISTINCT_NUMBERS = List.of(1, 1, 2, 2, 3, 3);

    private CollectionUtil underTest;

    @BeforeEach
    public void setUp() {
        underTest = new CollectionUtil();
    }

    @Test
    public void testCollectNonZeroValuesShouldReturnListOfOnlyNonZeroValues() {
        // given in setup

        // when
        List<Integer> result = underTest.collectNonZeroValues(LIST_OF_NUMBERS);

        // then
        assertEquals(LIST_OF_NON_ZERO_VALUES, result);
    }

    @Test
    public void testContainsOnlyDistinctValuesShouldReturnTrueWhenListContainsDistinctValues() {
        // given in setup

        // when
        boolean result = underTest.containsOnlyDistinctValues(LIST_OF_DISTINCT_NUMBERS);

        // then
        assertTrue(result);
    }

    @Test
    public void testContainsOnlyDistinctValuesShouldReturnFalseWhenListContainsRepeatedValues() {
        // given in setup

        // when
        boolean result = underTest.containsOnlyDistinctValues(LIST_OF_NON_DISTINCT_NUMBERS);

        // then
        assertFalse(result);
    }

}
