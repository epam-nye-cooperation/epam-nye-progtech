package hu.nye.progtech.sudoku.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.model.RawMap;
import hu.nye.progtech.sudoku.service.exception.MapSavingException;
import hu.nye.progtech.sudoku.service.map.parser.MapParser;
import hu.nye.progtech.sudoku.service.util.MapToStringUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class JdbcPersistenceTest {

    private JdbcPersistence underTest;

    private Connection connection;
    private MapToStringUtil mapToStringUtil;
    private MapParser mapParser;

    @BeforeEach
    public void init() throws SQLException {
        connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        mapToStringUtil = Mockito.mock(MapToStringUtil.class);
        mapParser = Mockito.mock(MapParser.class);
        underTest = new JdbcPersistence(connection, mapToStringUtil, mapParser);
        Mockito.verify(statement).execute(Mockito.anyString());
        Mockito.reset(connection, statement);
    }

    @Test
    public void testSaveShouldDeletePreviousSaveAndStoreTheNewOneWhenThereIsNoException() throws SQLException {
        // Given
        MapVO currentMap = Mockito.mock(MapVO.class);
        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(JdbcPersistence.INSERT_STATEMENT))
                .thenReturn(preparedStatement);
        String mapString = "mapString";
        Mockito.when(mapToStringUtil.convertMapVoMapToString(currentMap)).thenReturn(mapString);
        String fixedString = "fixedString";
        Mockito.when(mapToStringUtil.convertMapVoFixedToString(currentMap)).thenReturn(fixedString);

        // When
        underTest.save(currentMap);

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeUpdate(JdbcPersistence.DELETE_STATEMENT);
        Mockito.verify(statement).close();
        Mockito.verify(connection).prepareStatement(JdbcPersistence.INSERT_STATEMENT);
        Mockito.verify(mapToStringUtil).convertMapVoMapToString(currentMap);
        Mockito.verify(preparedStatement).setString(1, mapString);
        Mockito.verify(mapToStringUtil).convertMapVoFixedToString(currentMap);
        Mockito.verify(preparedStatement).setString(2, fixedString);
        Mockito.verify(preparedStatement).executeUpdate();
        Mockito.verify(preparedStatement).close();
        Mockito.verifyNoMoreInteractions(connection, mapToStringUtil, mapParser, currentMap,
                statement, preparedStatement);
    }

    @Test
    public void testSaveShouldDoNothingWhenThereIsAnSqlException() throws SQLException {
        // Given
        MapVO currentMap = Mockito.mock(MapVO.class);
        Mockito.when(connection.createStatement()).thenThrow(new SQLException());

        // When
        Assertions.assertThrows(MapSavingException.class, () -> underTest.save(currentMap));

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verifyNoMoreInteractions(connection, mapToStringUtil, mapParser, currentMap);
    }

    @Test
    public void testCloseShouldDelegateCloseCallToConnection() throws Exception {
        // Given

        // When
        underTest.close();

        // Then
        Mockito.verify(connection).close();
        Mockito.verifyNoMoreInteractions(connection, mapToStringUtil, mapParser);
    }

    @Test
    public void testLoadShouldReturnAMapVOWhenThereIsNoException() throws SQLException, MapSavingException {
        // Given
        MapVO expected = Mockito.mock(MapVO.class);
        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(statement.executeQuery(JdbcPersistence.SELECT_STATEMENT)).thenReturn(resultSet);
        String mapString = "mapString";
        Mockito.when(resultSet.getString("map")).thenReturn(mapString);
        String fixedString = "fixedString";
        Mockito.when(resultSet.getString("fixed")).thenReturn(fixedString);
        RawMap rawMap = new RawMap(mapString, fixedString);
        Mockito.when(mapParser.parseMap(rawMap)).thenReturn(expected);

        // When
        MapVO actual = underTest.load();

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(JdbcPersistence.SELECT_STATEMENT);
        Mockito.verify(resultSet).next();
        Mockito.verify(resultSet).getString("map");
        Mockito.verify(resultSet).getString("fixed");
        Mockito.verify(statement).close();
        Mockito.verify(resultSet).close();
        Mockito.verify(mapParser).parseMap(rawMap);
        Mockito.verifyNoMoreInteractions(connection, mapToStringUtil, mapParser, expected, statement, resultSet);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testLoadShouldThrowRuntimeExceptionWhenSqlExceptionIsThrown() throws SQLException {
        // Given
        MapVO expected = Mockito.mock(MapVO.class);
        Mockito.when(connection.createStatement()).thenThrow(new SQLException());

        // When
        Assertions.assertThrows(RuntimeException.class, () -> underTest.load());

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verifyNoMoreInteractions(connection, mapToStringUtil, mapParser, expected);
    }

    @Test
    public void testLoadShouldThrowRuntimeExceptionWhenMapParsingExceptionIsThrown() throws SQLException, MapSavingException {
        // Given
        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(statement.executeQuery(JdbcPersistence.SELECT_STATEMENT)).thenReturn(resultSet);
        String mapString = "mapString";
        Mockito.when(resultSet.getString("map")).thenReturn(mapString);
        String fixedString = "fixedString";
        Mockito.when(resultSet.getString("fixed")).thenReturn(fixedString);
        RawMap rawMap = new RawMap(mapString, fixedString);
        Mockito.when(mapParser.parseMap(rawMap)).thenThrow(MapSavingException.class);

        // When
        Assertions.assertThrows(RuntimeException.class, () -> underTest.load());

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(JdbcPersistence.SELECT_STATEMENT);
        Mockito.verify(resultSet).next();
        Mockito.verify(resultSet).getString("map");
        Mockito.verify(resultSet).getString("fixed");
        Mockito.verify(statement).close();
        Mockito.verify(resultSet).close();
        Mockito.verify(mapParser).parseMap(rawMap);
        Mockito.verifyNoMoreInteractions(connection, mapToStringUtil, mapParser, statement, resultSet);
    }

}
