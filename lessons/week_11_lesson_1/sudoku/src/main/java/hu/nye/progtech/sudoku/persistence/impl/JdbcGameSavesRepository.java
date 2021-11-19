package hu.nye.progtech.sudoku.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.model.RawMap;
import hu.nye.progtech.sudoku.persistence.GameSavesRepository;
import hu.nye.progtech.sudoku.service.exception.MapParsingException;
import hu.nye.progtech.sudoku.service.map.parser.MapParser;
import hu.nye.progtech.sudoku.service.util.MapToStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcGameSavesRepository implements GameSavesRepository, AutoCloseable {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcGameSavesRepository.class);

    private static final String INSERT_STATEMENT = "INSERT INTO game_saves (id, map, fixed) VALUES (1, ?, ?);";
    private static final String DELETE_STATEMENT = "DELETE FROM game_saves WHERE id = 1;";
    private static final String SELECT_STATEMENT = "SELECT * FROM game_saves WHERE id = 1;";

    private Connection connection;
    private MapToStringUtil mapToStringUtil;
    private MapParser mapParser;

    public JdbcGameSavesRepository(Connection connection, MapToStringUtil mapToStringUtil, MapParser mapParser) {
        this.connection = connection;
        this.mapToStringUtil = mapToStringUtil;
        this.mapParser = mapParser;
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
        RawMap rawMap = readRawMap();
        try {
            MapVO mapVO = mapParser.parseMap(rawMap);
            return mapVO;
        } catch (MapParsingException e) {
            throw new RuntimeException("Failed to parse loaded map");
        }
    }

    private RawMap readRawMap() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_STATEMENT);) {

            resultSet.next();
            String map = resultSet.getString("map");
            String fixed = resultSet.getString("fixed");

            RawMap rawMap = new RawMap(map, fixed);
            return rawMap;
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed to load map from DB");
        }
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
            preparedStatement.setString(1, mapToStringUtil.convertMapVoMapToString(currentMap));
            preparedStatement.setString(2, mapToStringUtil.convertMapVoFixedToString(currentMap));
            preparedStatement.executeUpdate();
        }
    }

}
