package hu.nye.progtech.sudoku.service.util;

import java.util.List;

import hu.nye.progtech.sudoku.model.MapVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MapUtilTest {

    private MapUtil underTest = new MapUtil();

    @Test
    public void testGetRowWithIndexShouldReturnTheSecondRowWhenIndexIsOne() {
        // Given
        MapVO mapVO = Mockito.mock(MapVO.class);
        int rowIndex = 1;
        List<Integer> expected = List.of(4,5,6);
        Mockito.when(mapVO.getMap()).thenReturn(new int[][]{{1,2,3},
                {4,5,6},
                {7,8,9}});
        Mockito.when(mapVO.getNumberOfColumns()).thenReturn(3);

        // When
        List<Integer> actual = underTest.getRowWithIndex(mapVO, rowIndex);

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(mapVO).getMap();
        Mockito.verify(mapVO, Mockito.times(4)).getNumberOfColumns();
        Mockito.verifyNoMoreInteractions(mapVO);
    }

    @Test
    public void testGetRowWithIndexShouldDoSomethingWhenMapVOIsNull() {
        // Given
        MapVO mapVO = null;
        int rowIndex = 1;

        // When - Then
        Assertions.assertThrows(NullPointerException.class, () -> underTest.getRowWithIndex(mapVO, rowIndex));
    }

}
