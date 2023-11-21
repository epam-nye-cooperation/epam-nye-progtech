package hu.nye.progtech.sudoku.service.map.reader.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

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

    private static final String LINE_1 = "line1";
    private static final String LINE_2 = "line2";

    private static final List<String> EXPECTED_RAW_MAP = List.of(LINE_1, LINE_2);

    @Mock
    private BufferedReader bufferedReader;

    private BufferedReaderMapReader underTest;

    @BeforeEach
    public void setUp() {
        underTest = new BufferedReaderMapReader(bufferedReader);
    }

    @Test
    public void testReadMapShouldReturnReadLinesFromBufferedReader() throws IOException, MapReadingException {
        // given
        given(bufferedReader.readLine()).willReturn(LINE_1, LINE_2, null);

        // when
        List<String> result = underTest.readMap();

        // then
        assertEquals(EXPECTED_RAW_MAP, result);
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
