package hu.nye.progtech.sudoku.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.model.RawMap;
import hu.nye.progtech.sudoku.persistence.Persistence;
import hu.nye.progtech.sudoku.persistence.model.GameSave;
import hu.nye.progtech.sudoku.service.exception.MapReadingException;
import hu.nye.progtech.sudoku.service.exception.MapSavingException;
import hu.nye.progtech.sudoku.service.map.parser.MapParser;
import hu.nye.progtech.sudoku.service.util.MapToStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JDBC based implementation of {@link Persistence}.
 */
public class JdbcPersistence implements Persistence, AutoCloseable {
    public static final String DBINIT  = "db-init.sql";
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcPersistence.class);

    static final String INSERT_STATEMENT = "INSERT INTO game_saves (id, map, fixed) VALUES ("+ GameSave.DEFAULT_ID +", ?, ?);";
    static final String DELETE_STATEMENT = "DELETE FROM game_saves WHERE id = "+ GameSave.DEFAULT_ID +";";
    static final String SELECT_STATEMENT = "SELECT * FROM game_saves WHERE id = "+ GameSave.DEFAULT_ID +";";

    private Connection connection;
    private MapToStringUtil mapToStringUtil;
    private MapParser mapParser;

    public JdbcPersistence(Connection connection, MapToStringUtil mapToStringUtil, MapParser mapParser) throws SQLException {
        this.connection = connection;
        this.mapToStringUtil = mapToStringUtil;
        this.mapParser = mapParser;
        init();
    }

    private void init() throws SQLException {
        String sql = "RUNSCRIPT FROM 'classpath:" + DBINIT + "'";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            LOGGER.info("Ensured that the " + DBINIT + " is loaded");
        }
    }

    @Override
    public void save(MapVO currentMap) {
        try {
            deleteCurrentlyStoredSave();
            insertNewSave(currentMap);
        } catch (SQLException e) {
            throw new MapSavingException("Unexpected exception during saving game state", e);
        }
    }

    @Override
    public MapVO load() {
        try {
            RawMap rawMap = readRawMap();
            MapVO mapVO = mapParser.parseMap(rawMap);
            return mapVO;
        } catch (SQLException e) {
            throw new MapReadingException("Failed to parse loaded map", e);
        }
    }

    private RawMap readRawMap() throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_STATEMENT);) {

            resultSet.next();
            String map = resultSet.getString("map");
            String fixed = resultSet.getString("fixed");

            RawMap rawMap = new RawMap(map, fixed);
            return rawMap;
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
