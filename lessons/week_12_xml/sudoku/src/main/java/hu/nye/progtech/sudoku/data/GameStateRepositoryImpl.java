package hu.nye.progtech.sudoku.data;

import hu.nye.progtech.sudoku.model.GameStateModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameStateRepositoryImpl implements GameStateRepository {

    private static Logger LOGGER = LoggerFactory.getLogger(GameStateRepositoryImpl.class);
    private static final String SAVE_QUERY = "INSERT INTO game_state (map, player_id) VALUES (?,?);";
    private static final String FIND_QUERY = "SELECT * FROM game_state WHERE id = ?;";
    private static final String FIND_ALL_BY_PLAYERS_QUERY = "SELECT * FROM game_state WHERE player_id = ?;";

    private final Connection connection;


    public GameStateRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(String map, int playerId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_QUERY)){
            preparedStatement.setString(1, map);
            preparedStatement.setInt(2, playerId);
            preparedStatement.executeUpdate();
        } catch (SQLException exception){
            LOGGER.error("Unexpected event during save!", exception);
        }
    }

    @Override
    public GameStateModel findById(int id) {
        List<GameStateModel> list = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_QUERY)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int gameStateId = resultSet.getInt("id");
                String map = resultSet.getString("map");
                int playerId = resultSet.getInt("player_id");
                list.add(new GameStateModel(gameStateId, map, playerId));
            }
        } catch (SQLException exception){
            LOGGER.error("Unexpected event during find by ID!", exception);
        }

        return list.size() == 1 ? list.get(0) : new GameStateModel();
    }

    @Override
    public List<GameStateModel> findAllByPlayer(int playerId) {
        List<GameStateModel> list = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_PLAYERS_QUERY)){
            preparedStatement.setInt(1, playerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int gameStateId = resultSet.getInt("id");
                String map = resultSet.getString("map");
                int playerId2 = resultSet.getInt("player_id");
                list.add(new GameStateModel(gameStateId, map, playerId2));
            }
        } catch (SQLException exception){
            LOGGER.error("Unexpected event during find by ID!", exception);
        }

        return list;
    }
}
