package hu.nye.progtech.sudoku.persistence.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hu.nye.progtech.sudoku.core.model.MapVO;
import hu.nye.progtech.sudoku.core.model.RawMap;
import hu.nye.progtech.sudoku.core.persistence.api.GameSavesRepository;
import hu.nye.progtech.sudoku.core.service.exception.MapParsingException;
import hu.nye.progtech.sudoku.core.service.map.parser.MapParser;
import hu.nye.progtech.sudoku.core.service.util.MapToStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JDBC based implementation of {@link GameSavesRepository}.
 */
public class JdbcGameSavesRepository implements GameSavesRepository, AutoCloseable {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcGameSavesRepository.class);

    static final String INSERT_STATEMENT = "INSERT INTO game_saves (username, map, fixed) VALUES (?, ?, ?);";
    static final String DELETE_STATEMENT = "DELETE FROM game_saves WHERE username = ?;";
    static final String SELECT_STATEMENT = "SELECT * FROM game_saves WHERE username = ?;";

    private Connection connection;
    private MapToStringUtil mapToStringUtil;
    private MapParser mapParser;

    public JdbcGameSavesRepository(Connection connection, MapToStringUtil mapToStringUtil, MapParser mapParser) {
        this.connection = connection;
        this.mapToStringUtil = mapToStringUtil;
        this.mapParser = mapParser;
    }

    @Override
    public void save(String username, MapVO currentMap) {
        try {
            deleteCurrentlyStoredSave(username);
            insertNewSave(username, currentMap);
        } catch (SQLException e) {
            LOGGER.error("Unexpected exception during saving game state", e);
        }
    }

    @Override
    public MapVO load(String username) {
        RawMap rawMap = readRawMap(username);
        try {
            MapVO mapVO = mapParser.parseMap(rawMap);
            return mapVO;
        } catch (MapParsingException e) {
            throw new RuntimeException("Failed to parse loaded map");
        }
    }

    private RawMap readRawMap(String username) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STATEMENT)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                resultSet.next();
                String map = resultSet.getString("map");
                String fixed = resultSet.getString("fixed");

                RawMap rawMap = new RawMap(map, fixed);
                return rawMap;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load map from DB", e);
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    private void deleteCurrentlyStoredSave(String username) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STATEMENT)) {
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        }
    }

    private void insertNewSave(String username, MapVO currentMap) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STATEMENT)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, mapToStringUtil.convertMapVoMapToString(currentMap));
            preparedStatement.setString(3, mapToStringUtil.convertMapVoFixedToString(currentMap));
            preparedStatement.executeUpdate();
        }
    }

}
