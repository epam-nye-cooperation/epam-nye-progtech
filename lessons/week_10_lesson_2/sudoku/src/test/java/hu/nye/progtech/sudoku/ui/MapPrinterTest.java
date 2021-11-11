package hu.nye.progtech.sudoku.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.service.util.MapUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link MapPrinter}.
 */
@ExtendWith(MockitoExtension.class)
public class MapPrinterTest {

    private static final int NUMBER_OF_ROWS = 4;
    private static final int NUMBER_OF_COLUMNS = 4;
    private static final int BOX_WIDTH = 2;
    private static final int BOX_HEIGHT = 2;

    private static final int[][] MAP = {
        {0, 1, 2, 3},
        {0, 1, 2, 3},
        {0, 1, 2, 3},
        {0, 1, 2, 3},
    };

    private static final MapVO MAP_VO = new MapVO(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, MAP, null);

    private static final List<List<Integer>> ROWS_AS_LISTS = List.of(
        List.of(0, 1, 2, 3),
        List.of(0, 1, 2, 3),
        List.of(0, 1, 2, 3),
        List.of(0, 1, 2, 3)
    );

    private static final List<String> MAP_AS_STRING = List.of(
        "====================",
        "||   | 1 || 2 | 3 ||",
        "||   | 1 || 2 | 3 ||",
        "====================",
        "||   | 1 || 2 | 3 ||",
        "||   | 1 || 2 | 3 ||",
        "===================="
    );

    @Mock
    private MapUtil mapUtil;
    @Mock
    private PrintWrapper printWrapper;
    @Captor
    private ArgumentCaptor<String> printWrapperArgumentCaptor;

    private MapPrinter underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MapPrinter(BOX_WIDTH, BOX_HEIGHT, mapUtil, printWrapper);
    }

    @Test
    public void testPrintMapShouldDelegateCorrectCallsToPrintWrapper() {
        // given
        for (int i = 0; i < ROWS_AS_LISTS.size(); i++) {
            given(mapUtil.getRowOfMap(MAP_VO, i)).willReturn(ROWS_AS_LISTS.get(i));
        }

        // when
        underTest.printMap(MAP_VO);

        // then
        verify(printWrapper, times(7)).printLine(printWrapperArgumentCaptor.capture());
        assertEquals(MAP_AS_STRING, printWrapperArgumentCaptor.getAllValues());
    }

}
