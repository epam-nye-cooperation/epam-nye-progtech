package hu.nye.progtech.database.repository;

import hu.nye.progtech.database.entity.HighScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HighScoreRepository extends JpaRepository<HighScore, Long> {

    HighScore findByPlayerName(String playerName);
}
