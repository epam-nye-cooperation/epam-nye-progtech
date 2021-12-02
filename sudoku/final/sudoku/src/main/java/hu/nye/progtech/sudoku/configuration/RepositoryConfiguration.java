package hu.nye.progtech.sudoku.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import hu.nye.progtech.sudoku.persistence.GameSavesRepository;
import hu.nye.progtech.sudoku.persistence.impl.AdvancedXmlGameSavesRepository;
import hu.nye.progtech.sudoku.persistence.impl.JdbcGameSavesRepository;
import hu.nye.progtech.sudoku.persistence.impl.XmlGameSavesRepository;
import hu.nye.progtech.sudoku.service.map.parser.MapParser;
import hu.nye.progtech.sudoku.service.util.MapToStringUtil;
import hu.nye.progtech.sudoku.xml.converter.MapVOToXmlMapVOConverter;
import hu.nye.progtech.sudoku.xml.converter.XmlMapVOToMapVOConverter;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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
    GameSavesRepository jdbcGameSavesRepository(Connection connection, MapToStringUtil mapToStringUtil, MapParser mapParser) {
        return new JdbcGameSavesRepository(connection, mapToStringUtil, mapParser);
    }

    @Bean
    GameSavesRepository xmlGameSavesRepository(Marshaller marshaller, Unmarshaller unmarshaller) {
        return new XmlGameSavesRepository(marshaller, unmarshaller);
    }

    @Bean
    @Primary
    GameSavesRepository advancedXmlGameSavesRepository(Marshaller marshaller, Unmarshaller unmarshaller, MapVOToXmlMapVOConverter
            mapVOToXmlMapVOConverter, XmlMapVOToMapVOConverter xmlMapVOToMapVOConverter) {
        return new AdvancedXmlGameSavesRepository(marshaller, unmarshaller, mapVOToXmlMapVOConverter,
                xmlMapVOToMapVOConverter);
    }

}
