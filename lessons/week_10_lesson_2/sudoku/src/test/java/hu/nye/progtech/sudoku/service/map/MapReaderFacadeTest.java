package hu.nye.progtech.sudoku.service.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.util.List;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.service.exception.MapParsingException;
import hu.nye.progtech.sudoku.service.exception.MapReadingException;
import hu.nye.progtech.sudoku.service.exception.MapValidationException;
import hu.nye.progtech.sudoku.service.map.parser.MapParser;
import hu.nye.progtech.sudoku.service.map.reader.MapReader;
import hu.nye.progtech.sudoku.service.validator.MapValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link MapReaderFacade}.
 */
@ExtendWith(MockitoExtension.class)
public class MapReaderFacadeTest {

    private static final List<String> RAW_MAP = List.of(
        "row1",
        "row2"
    );

    private static final MapVO MAP_VO = new MapVO(0, 0, null, null);

    @Mock
    private MapReader mapReader;
    @Mock
    private MapParser mapParser;
    @Mock
    private MapValidator mapValidator;

    private MapReaderFacade underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MapReaderFacade(mapReader, mapParser, mapValidator);
    }

    @Test
    public void testReadMapShouldReturnReadAndParsedAndValidatedMap() throws MapReadingException, MapParsingException, MapValidationException {
        // given
        given(mapReader.readMap()).willReturn(RAW_MAP);
        given(mapParser.parseMap(RAW_MAP)).willReturn(MAP_VO);

        // when
        MapVO result = underTest.readMap();

        // then
        verify(mapReader).readMap();
        verify(mapParser).parseMap(RAW_MAP);
        verify(mapValidator).validate(MAP_VO);
        assertEquals(MAP_VO, result);
    }

    @Test
    public void testReadMapShouldThrowRuntimeExceptionWhenReadingOfTheMapFails() throws MapReadingException {
        // given
        doThrow(MapReadingException.class).when(mapReader).readMap();

        // when - then
        assertThrows(RuntimeException.class, () -> {
            underTest.readMap();
        });
    }

    @Test
    public void testReadMapShouldThrowRuntimeExceptionWhenParsingOfTheMapFails() throws MapReadingException, MapParsingException {
        // given
        given(mapReader.readMap()).willReturn(RAW_MAP);
        doThrow(MapParsingException.class).when(mapParser).parseMap(RAW_MAP);

        // when - then
        assertThrows(RuntimeException.class, () -> {
            underTest.readMap();
        });
    }

    @Test
    public void testReadMapShouldThrowRuntimeExceptionWhenValidationOfTheMapFails() throws MapReadingException, MapParsingException, MapValidationException {
        // given
        given(mapReader.readMap()).willReturn(RAW_MAP);
        given(mapParser.parseMap(RAW_MAP)).willReturn(MAP_VO);
        doThrow(MapValidationException.class).when(mapValidator).validate(MAP_VO);

        // when - then
        assertThrows(RuntimeException.class, () -> {
            underTest.readMap();
        });
    }

}
