package hu.nye.progtech.sudoku.persistence;

import hu.nye.progtech.sudoku.persistence.model.GameSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameSaveRepository extends JpaRepository<GameSave, Integer> {
}