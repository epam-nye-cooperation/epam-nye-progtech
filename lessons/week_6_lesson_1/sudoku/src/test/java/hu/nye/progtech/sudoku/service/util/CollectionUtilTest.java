package hu.nye.progtech.sudoku.service.util;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CollectionUtilTest {

    private CollectionUtil underTest = new CollectionUtil();

    @Test
    public void testGetNonZeroValuesShouldReturnAListWhichDoesNotContainsZeroValuesWhenTheIntegerListContainsZero() {
        // Given
        List<Integer> integerList = List.of(0,1,2,3,0);
        List<Integer> expected = List.of(1,2,3);

        // When
        List<Integer> actual = underTest.getNonZeroValues(integerList);

        // Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetNonZeroValuesShouldReturnAnEmptyListWhenTheIntegerListContainsOnlyZeroValue() {
        // Given
        List<Integer> integerList = List.of(0,0,0,0,0);
        List<Integer> expected = List.of();

        // When
        List<Integer> actual = underTest.getNonZeroValues(integerList);

        // Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetNonZeroValuesShouldReturnAnEmptyListWhenTheIntegerListIsEmpty() {
        // Given
        List<Integer> integerList = List.of();
        List<Integer> expected = List.of();

        // When
        List<Integer> actual = underTest.getNonZeroValues(integerList);

        // Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testContainsOnlyDistinctValuesShouldReturnTrueWhenIntegerListContainsDistinctValues () {
        // Given
        List<Integer> integerList = List.of(1,2,3,4,5);
        boolean expected = true;

        // When
        boolean actual = underTest.containsOnlyDistinctValues(integerList);

        // Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testContainsOnlyDistinctValuesShouldReturnFalseWhenIntegerListContainsNotOnlyDistinctValues () {
        // Given
        List<Integer> integerList = List.of(1,2,1,4,5);
        boolean expected = false;

        // When
        boolean actual = underTest.containsOnlyDistinctValues(integerList);

        // Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testContainsOnlyDistinctValuesShouldReturnTrueWhenIntegerListIsEmpty () {
        // Given
        List<Integer> integerList = List.of();
        boolean expected = true;

        // When
        boolean actual = underTest.containsOnlyDistinctValues(integerList);

        // Then
        Assertions.assertEquals(expected, actual);
    }

}
