package hu.nye.progtech.service;

import hu.nye.progtech.database.entity.HighScore;
import hu.nye.progtech.database.repository.HighScoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HighScoreServiceTest {

    @Mock
    private HighScoreRepository highScoreRepository;
    @Mock
    private ConsoleService consoleService;
    private HighScoreService underTest;

    @BeforeEach
    public void setup() {
        underTest = new HighScoreService(consoleService, highScoreRepository);
    }

    @Test
    public void saveShouldCallHighScoreRepository() {
        // GIVEN
        HighScore highScore = new HighScore("alma", 0);
        when(highScoreRepository.save(highScore)).thenReturn(highScore);

        // WHEN
        HighScore actual = underTest.save(highScore);

        // THEN
        assertEquals(highScore, actual);
    }

    @Test
    public void findByPlayerNameOrCreateShouldFindTheHighScoreForThePlayer() {
        // GIVEN
        final String playerName = "alma";
        final HighScore highScore = new HighScore(playerName, 0);
        when(highScoreRepository.findByPlayerName(playerName)).thenReturn(highScore);

        // WHEN
        final HighScore actual = underTest.findByPlayerNameOrCreate(playerName);

        // THEN
        assertEquals(highScore, actual);
    }

    @Test
    public void findByPlayerNameOrCreateShouldSaveTheHighScoreForThePlayer() {
        // GIVEN
        final String playerName = "alma";
        final HighScore highScore = new HighScore(playerName, 0);
        when(highScoreRepository.findByPlayerName(playerName)).thenReturn(null);
        when(highScoreRepository.save(any())).thenReturn(highScore);

        // WHEN
        final HighScore actual = underTest.findByPlayerNameOrCreate(playerName);

        // THEN
        assertEquals(highScore, actual);
    }
}
