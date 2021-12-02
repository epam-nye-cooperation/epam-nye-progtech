package hu.nye.progtech.sudoku.persistence.xml.configuration;

import hu.nye.progtech.sudoku.core.persistence.api.GameSavesRepository;
import hu.nye.progtech.sudoku.persistence.xml.converter.MapVOToXmlMapVOConverter;
import hu.nye.progtech.sudoku.persistence.xml.converter.XmlMapVOToMapVOConverter;
import hu.nye.progtech.sudoku.persistence.xml.model.XmlMapVO;
import hu.nye.progtech.sudoku.persistence.xml.repository.XmlGameSavesRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class XmlRepositoryConfiguration {

    @Bean
    Marshaller marshaller() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(XmlMapVO.class);

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        return marshaller;
    }

    @Bean
    Unmarshaller unmarshaller() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(XmlMapVO.class);

        return jaxbContext.createUnmarshaller();
    }

    @Bean
    MapVOToXmlMapVOConverter mapVOToXmlMapVOConverter() {
        return new MapVOToXmlMapVOConverter();
    }

    @Bean
    XmlMapVOToMapVOConverter xmlMapVOToMapVOConverter() {
        return new XmlMapVOToMapVOConverter();
    }

    @Bean
    @Primary
    GameSavesRepository xmlGameSavesRepository(Marshaller marshaller, Unmarshaller unmarshaller, MapVOToXmlMapVOConverter
            mapVOToXmlMapVOConverter, XmlMapVOToMapVOConverter xmlMapVOToMapVOConverter) {
        return new XmlGameSavesRepository(marshaller, unmarshaller, mapVOToXmlMapVOConverter,
                xmlMapVOToMapVOConverter);
    }

}
