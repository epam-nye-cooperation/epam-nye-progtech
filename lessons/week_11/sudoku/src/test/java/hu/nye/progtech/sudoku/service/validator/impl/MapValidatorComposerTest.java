package hu.nye.progtech.sudoku.service.validator.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.util.List;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.service.exception.MapValidationException;
import hu.nye.progtech.sudoku.service.validator.MapValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link MapValidatorComposer}.
 */
@ExtendWith(MockitoExtension.class)
public class MapValidatorComposerTest {

    private static final MapVO MAP_VO = new MapVO(0, 0, null, null);

    @Mock
    private MapValidator validator1;
    @Mock
    private MapValidator validator2;

    private MapValidatorComposer underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MapValidatorComposer(List.of(validator1, validator2));
    }

    @Test
    public void testValidateShouldNotThrowExceptionWhenNeitherOfTheValidatorsThrowException() throws MapValidationException {
        // given in setup

        // when
        underTest.validate(MAP_VO);

        // then
        verify(validator1).validate(MAP_VO);
        verify(validator2).validate(MAP_VO);
    }

    @Test
    public void testValidateShouldThrowExceptionWhenAnyOfTheValidatorsThrowException() throws MapValidationException {
        // given in setup
        doThrow(MapValidationException.class).when(validator2).validate(MAP_VO);

        // when - then
        assertThrows(MapValidationException.class, () -> {
            underTest.validate(MAP_VO);
        });
    }

}
