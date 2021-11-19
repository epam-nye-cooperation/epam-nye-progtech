package hu.nye.progtech.sudoku.service.validator.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.Collections;
import java.util.List;

import hu.nye.progtech.sudoku.model.BoxDescription;
import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.service.exception.InvalidBoxException;
import hu.nye.progtech.sudoku.service.exception.MapValidationException;
import hu.nye.progtech.sudoku.service.util.CollectionUtil;
import hu.nye.progtech.sudoku.service.util.MapUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link MapByBoxValidator}.
 */
@ExtendWith(MockitoExtension.class)
public class MapByBoxValidatorTest {

    private static final BoxDescription BOX_DESCRIPTION = new BoxDescription(0, 0, 0, 0);
    private static final List<BoxDescription> BOX_DESCRIPTION_LIST = List.of(BOX_DESCRIPTION);

    private static final MapVO MAP_VO = new MapVO(0, 0, null, null);

    private static final List<Integer> BOX_VALUES = Collections.emptyList();
    private static final List<Integer> NON_ZERO_VALUES = Collections.emptyList();

    @Mock
    private MapUtil mapUtil;
    @Mock
    private CollectionUtil collectionUtil;

    private MapByBoxValidator underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MapByBoxValidator(BOX_DESCRIPTION_LIST, mapUtil, collectionUtil);
    }

    @Test
    public void testValidateShouldNotThrowExceptionWhenCheckedBoxesAreValid() throws MapValidationException {
        // given
        given(mapUtil.getBoxValuesOfMap(MAP_VO, BOX_DESCRIPTION)).willReturn(BOX_VALUES);
        given(collectionUtil.collectNonZeroValues(BOX_VALUES)).willReturn(NON_ZERO_VALUES);
        given(collectionUtil.containsOnlyDistinctValues(NON_ZERO_VALUES)).willReturn(true);

        // when
        underTest.validate(MAP_VO);

        // then no exception is thrown
    }

    @Test
    public void testValidateShouldThrowInvalidColumnExceptionWhenAnyOfTheCheckedBoxesIsNotValid() {
        // given
        given(mapUtil.getBoxValuesOfMap(MAP_VO, BOX_DESCRIPTION)).willReturn(BOX_VALUES);
        given(collectionUtil.collectNonZeroValues(BOX_VALUES)).willReturn(NON_ZERO_VALUES);
        given(collectionUtil.containsOnlyDistinctValues(NON_ZERO_VALUES)).willReturn(false);

        // when - then
        assertThrows(InvalidBoxException.class, () -> {
            underTest.validate(MAP_VO);
        });
    }

}
