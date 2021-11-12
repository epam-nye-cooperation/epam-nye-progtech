package hu.nye.progtech.sudoku.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringJoiner;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.persistence.GameSavesRepository;
import hu.nye.progtech.sudoku.service.util.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcGameSavesRepository implements GameSavesRepository, AutoCloseable {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcGameSavesRepository.class);

    private static final String INSERT_STATEMENT = "INSERT INTO game_saves (id, map, fixed) VALUES (1, ?, ?);";
    private static final String DELETE_STATEMENT = "DELETE FROM game_saves WHERE id = 1;";
    private static final String SELECT_STATEMENT = "SELECT * FROM game_saves WHERE id = 1;";

    private Connection connection;
    private MapUtil mapUtil;

    public JdbcGameSavesRepository(Connection connection, MapUtil mapUtil) {
        this.connection = connection;
    }

    @Override
    public void save(MapVO currentMap) {
        try {
            deleteCurrentlyStoredSave();
            insertNewSave(currentMap);
        } catch (SQLException e) {
            LOGGER.error("Unexpected exception during saving game state", e);
        }
    }

    @Override
    public MapVO load() {
        return null;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    private void deleteCurrentlyStoredSave() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(DELETE_STATEMENT);
        }
    }

    private void insertNewSave(MapVO currentMap) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STATEMENT)) {
            preparedStatement.setString(0, convertMapVoMapToString(currentMap));
            preparedStatement.setString(1, convertMapVoFixedToString(currentMap));
            preparedStatement.executeUpdate();
        }
    }

    private String convertMapVoMapToString(MapVO mapVO) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (Integer integer : mapUtil.getRowOfMap(mapVO, i)) {
                builder.append(integer);
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private String convertMapVoFixedToString(MapVO currentMap) {
        return "";
    }

}
