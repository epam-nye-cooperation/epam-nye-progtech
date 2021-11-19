package hu.nye.progtech.sudoku.service.validator.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.Collections;
import java.util.List;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.service.exception.InvalidRowException;
import hu.nye.progtech.sudoku.service.exception.MapValidationException;
import hu.nye.progtech.sudoku.service.util.CollectionUtil;
import hu.nye.progtech.sudoku.service.util.MapUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link MapByRowValidator}.
 */
@ExtendWith(MockitoExtension.class)
public class MapByRowValidatorTest {

    private static final MapVO MAP_VO = new MapVO(1, 1, null, null);

    private static final int ROW_INDEX = 0;

    private static final List<Integer> ROW_VALUES = Collections.emptyList();
    private static final List<Integer> NON_ZERO_VALUES = Collections.emptyList();

    @Mock
    private MapUtil mapUtil;
    @Mock
    private CollectionUtil collectionUtil;

    private MapByRowValidator underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MapByRowValidator(collectionUtil, mapUtil);
    }

    @Test
    public void testValidateShouldNotThrowExceptionWhenCheckedRowsAreValid() throws MapValidationException {
        // given
        given(mapUtil.getRowOfMap(MAP_VO, ROW_INDEX)).willReturn(ROW_VALUES);
        given(collectionUtil.collectNonZeroValues(ROW_VALUES)).willReturn(NON_ZERO_VALUES);
        given(collectionUtil.containsOnlyDistinctValues(NON_ZERO_VALUES)).willReturn(true);

        // when
        underTest.validate(MAP_VO);

        // then no exception is thrown
    }

    @Test
    public void testValidateShouldThrowInvalidRowExceptionWhenAnyOfTheCheckedRowsIsNotValid() {
        // given
        given(mapUtil.getRowOfMap(MAP_VO, ROW_INDEX)).willReturn(ROW_VALUES);
        given(collectionUtil.collectNonZeroValues(ROW_VALUES)).willReturn(NON_ZERO_VALUES);
        given(collectionUtil.containsOnlyDistinctValues(NON_ZERO_VALUES)).willReturn(false);

        // when - then
        assertThrows(InvalidRowException.class, () -> {
            underTest.validate(MAP_VO);
        });
    }

}
