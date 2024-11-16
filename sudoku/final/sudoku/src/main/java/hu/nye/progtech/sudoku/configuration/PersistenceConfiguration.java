package hu.nye.progtech.sudoku.configuration;

import java.sql.SQLException;

import hu.nye.progtech.sudoku.persistence.GameSaveRepository;
import hu.nye.progtech.sudoku.persistence.Persistence;
import hu.nye.progtech.sudoku.persistence.impl.AdvancedXmlPersistence;
import hu.nye.progtech.sudoku.persistence.impl.JdbcPersistence;
import hu.nye.progtech.sudoku.persistence.impl.SDPersistence;
import hu.nye.progtech.sudoku.persistence.impl.XmlPersistence;
import hu.nye.progtech.sudoku.service.map.parser.MapParser;
import hu.nye.progtech.sudoku.service.util.MapToStringUtil;
import hu.nye.progtech.sudoku.xml.converter.MapVOToXmlMapVOConverter;
import hu.nye.progtech.sudoku.xml.converter.XmlMapVOToMapVOConverter;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Spring Java configuration class for persistence layer specific Spring Beans.
 */
@Configuration
public class PersistenceConfiguration {

    @Bean(destroyMethod = "close")
    Persistence jdbcGameSavesRepository(DataSource source, MapToStringUtil mapToStringUtil,
                                        MapParser mapParser) throws SQLException {
        return new JdbcPersistence(source.getConnection(), mapToStringUtil, mapParser);
    }

    @Bean
    Persistence sdGameSavesRepository(DataSource source, GameSaveRepository repo,
                                      MapToStringUtil mapToStringUtil, MapParser mapParser) {
        return new SDPersistence(source, repo, mapToStringUtil, mapParser);
    }

    @Bean
    Persistence xmlGameSavesRepository(Marshaller marshaller, Unmarshaller unmarshaller) {
        return new XmlPersistence(marshaller, unmarshaller);
    }

    @Bean
    @Primary
    Persistence advancedXmlGameSavesRepository(Marshaller marshaller, Unmarshaller unmarshaller, MapVOToXmlMapVOConverter
            mapVOToXmlMapVOConverter, XmlMapVOToMapVOConverter xmlMapVOToMapVOConverter) {
        return new AdvancedXmlPersistence(marshaller, unmarshaller, mapVOToXmlMapVOConverter,
                xmlMapVOToMapVOConverter);
    }

}
