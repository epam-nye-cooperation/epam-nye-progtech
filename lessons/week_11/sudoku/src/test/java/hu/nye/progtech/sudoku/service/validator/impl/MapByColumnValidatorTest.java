package hu.nye.progtech.sudoku.service.validator.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.Collections;
import java.util.List;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.service.exception.InvalidColumnException;
import hu.nye.progtech.sudoku.service.exception.MapValidationException;
import hu.nye.progtech.sudoku.service.util.CollectionUtil;
import hu.nye.progtech.sudoku.service.util.MapUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link MapByColumnValidator}.
 */
@ExtendWith(MockitoExtension.class)
public class MapByColumnValidatorTest {

    private static final MapVO MAP_VO = new MapVO(1, 1, null, null);

    private static final int COLUMN_INDEX = 0;

    private static final List<Integer> COLUMN_VALUES = Collections.emptyList();
    private static final List<Integer> NON_ZERO_VALUES = Collections.emptyList();

    @Mock
    private MapUtil mapUtil;
    @Mock
    private CollectionUtil collectionUtil;

    private MapByColumnValidator underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MapByColumnValidator(collectionUtil, mapUtil);
    }

    @Test
    public void testValidateShouldNotThrowExceptionWhenCheckedColumnsAreValid() throws MapValidationException {
        // given
        given(mapUtil.getColumnOfMap(MAP_VO, COLUMN_INDEX)).willReturn(COLUMN_VALUES);
        given(collectionUtil.collectNonZeroValues(COLUMN_VALUES)).willReturn(NON_ZERO_VALUES);
        given(collectionUtil.containsOnlyDistinctValues(NON_ZERO_VALUES)).willReturn(true);

        // when
        underTest.validate(MAP_VO);

        // then no exception is thrown
    }

    @Test
    public void testValidateShouldThrowInvalidColumnExceptionWhenAnyOfTheCheckedColumnsIsNotValid() {
        // given
        given(mapUtil.getColumnOfMap(MAP_VO, COLUMN_INDEX)).willReturn(COLUMN_VALUES);
        given(collectionUtil.collectNonZeroValues(COLUMN_VALUES)).willReturn(NON_ZERO_VALUES);
        given(collectionUtil.containsOnlyDistinctValues(NON_ZERO_VALUES)).willReturn(false);

        // when - then
        assertThrows(InvalidColumnException.class, () -> {
            underTest.validate(MAP_VO);
        });
    }

}
