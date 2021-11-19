package hu.nye.progtech.sudoku.persistence.impl;

import java.sql.Connection;

import hu.nye.progtech.sudoku.service.map.parser.MapParser;
import hu.nye.progtech.sudoku.service.util.MapToStringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class JdbcGameSavesRepositoryTest {

    private JdbcGameSavesRepository underTest;

    private Connection connection;
    private MapToStringUtil mapToStringUtil;
    private MapParser mapParser;

    @BeforeEach
    public void init() {
        connection = Mockito.mock(Connection.class);
        mapToStringUtil = Mockito.mock(MapToStringUtil.class);
        mapParser = Mockito.mock(MapParser.class);

        underTest = new JdbcGameSavesRepository(connection, mapToStringUtil, mapParser);
    }

    @Test
    public void testSaveShouldDeletePreviousSaveAndStoreTheNewOneWhenThereIsNoException() {
        // Given

        // When

        // Then

    }

}
