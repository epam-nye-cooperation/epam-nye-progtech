package hu.nye.progtech.sudoku.persistence.jdbc.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import hu.nye.progtech.sudoku.core.persistence.api.GameSavesRepository;
import hu.nye.progtech.sudoku.persistence.jdbc.repository.JdbcGameSavesRepository;
import hu.nye.progtech.sudoku.core.service.map.parser.MapParser;
import hu.nye.progtech.sudoku.core.service.util.MapToStringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Java configuration class for persistence layer specific Spring Beans.
 */
@Configuration
public class JdbcRepositoryConfiguration {

    @Bean
    Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
    }

    @Bean(destroyMethod = "close")
    GameSavesRepository jdbcGameSavesRepository(Connection connection, MapToStringUtil mapToStringUtil, MapParser mapParser) {
        return new JdbcGameSavesRepository(connection, mapToStringUtil, mapParser);
    }

}
