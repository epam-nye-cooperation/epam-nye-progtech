package hu.nye.progtech.sudoku.service.map.reader.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import hu.nye.progtech.sudoku.model.RawMap;
import hu.nye.progtech.sudoku.service.exception.MapReadingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link BufferedReaderMapReader}.
 */
@ExtendWith(MockitoExtension.class)
public class BufferedReaderMapReaderTest {

    private static final String LINE_1 = "0012";
    private static final String LINE_2 = "2100";

    private static final String EXPECTED_MAP = "0012\n2100\n";
    private static final String EXPECTED_FIXED = "0011\n1100\n";

    @Mock
    private BufferedReader bufferedReader;

    private BufferedReaderMapReader underTest;

    @BeforeEach
    public void setUp() {
        underTest = new BufferedReaderMapReader(bufferedReader);
    }

    @Test
    public void testReadMapShouldReturnRawMapFromBufferedReader() throws IOException, MapReadingException {
        // given
        given(bufferedReader.readLine()).willReturn(LINE_1, LINE_2, null);
        RawMap expected = new RawMap(EXPECTED_MAP, EXPECTED_FIXED);

        // when
        RawMap actual = underTest.readMap();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testReadMapShouldThrowMapReadingExceptionWhenMapReadingFails() throws IOException {
        // given
        doThrow(IOException.class).when(bufferedReader).readLine();

        // when - then
        assertThrows(MapReadingException.class, () -> {
            underTest.readMap();
        });
    }

}
