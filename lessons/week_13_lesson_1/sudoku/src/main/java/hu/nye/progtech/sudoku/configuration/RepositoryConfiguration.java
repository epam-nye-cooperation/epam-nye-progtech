package hu.nye.progtech.sudoku.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import hu.nye.progtech.sudoku.persistence.GameSavesRepository;
import hu.nye.progtech.sudoku.persistence.impl.JdbcGameSavesRepository;
import hu.nye.progtech.sudoku.service.map.parser.MapParser;
import hu.nye.progtech.sudoku.service.util.MapToStringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Java configuration class for persistence layer specific Spring Beans.
 */
@Configuration
public class RepositoryConfiguration {

    @Bean
    Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
    }

    @Bean(destroyMethod = "close")
    GameSavesRepository gameSavesRepository(Connection connection, MapToStringUtil mapToStringUtil, MapParser mapParser) {
        return new JdbcGameSavesRepository(connection, mapToStringUtil, mapParser);
    }

}
