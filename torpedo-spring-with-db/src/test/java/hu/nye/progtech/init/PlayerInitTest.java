package hu.nye.progtech.init;

import hu.nye.progtech.domain.Player;
import hu.nye.progtech.service.ConsoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerInitTest {
    @Mock
    private ConsoleService consoleServiceMock;

    private PlayerInit underTest;

    @BeforeEach
    public void setup() {
        underTest = new PlayerInit(consoleServiceMock);
    }

    @Test
    public void readPlayerDetailsShouldReturnPlayer() {
        // GIVEN
        final String expectedPlayerName = "alma";
        when(consoleServiceMock.readStringFromConsole(anyString())).thenReturn(expectedPlayerName);

        // WHEN
        final Player actualPlayer = underTest.readPlayerDetails();

        // THEN
        assertEquals(expectedPlayerName, actualPlayer.getName());
    }
}
