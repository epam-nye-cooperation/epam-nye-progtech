package hu.nye.progtech.sudoku.service.command;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link InputHandler}.
 */
@ExtendWith(MockitoExtension.class)
public class InputHandlerTest {

    private static final String INPUT = "input";

    @Mock
    private Command command1;
    @Mock
    private Command command2;

    private InputHandler underTest;

    @BeforeEach
    public void setUp() {
        underTest = new InputHandler(List.of(command1, command2));
    }

    @Test
    public void testHandleInputShouldRunOnlyTheFirstApplicableCommand() {
        // given
        given(command1.canProcess(INPUT)).willReturn(true);

        // when
        underTest.handleInput(INPUT);

        // then
        verify(command1).canProcess(INPUT);
        verify(command1).process(INPUT);
        verifyNoInteractions(command2);
    }

    @Test
    public void testHandleInputShouldRunNoCommandsWhenNoneOfThemIsApplicable() {
        // given
        given(command1.canProcess(INPUT)).willReturn(false);
        given(command2.canProcess(INPUT)).willReturn(false);

        // when
        underTest.handleInput(INPUT);

        // then
        verify(command1).canProcess(INPUT);
        verifyNoMoreInteractions(command1);
        verify(command2).canProcess(INPUT);
        verifyNoMoreInteractions(command2);
    }

}
