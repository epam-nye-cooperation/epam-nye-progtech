package hu.nye.progtech.service;

import hu.nye.progtech.database.entity.HighScore;
import hu.nye.progtech.database.repository.HighScoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HighScoreService {

    private final ConsoleService consoleService;
    private final HighScoreRepository highScoreRepository;

    public HighScore findByPlayerNameOrCreate(String playerName) {
        HighScore highScore = highScoreRepository.findByPlayerName(playerName);
        if (highScore == null) {
            highScore = highScoreRepository.save(new HighScore(playerName, 0));
        }
        consoleService.print("Hi " + playerName + ", your high score is " + highScore.getGamesWon());
        return highScore;
    }

    public HighScore save(HighScore highScore) {
        return highScoreRepository.save(highScore);
    }
}
