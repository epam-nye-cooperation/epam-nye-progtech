package hu.nye.progtech.sudoku.configuration;

import hu.nye.progtech.sudoku.xml.converter.MapVOToXmlMapVOConverter;
import hu.nye.progtech.sudoku.xml.converter.XmlMapVOToMapVOConverter;
import hu.nye.progtech.sudoku.xml.model.XmlMapVO;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for XML related components.
 */
@Configuration
public class XmlConfiguration {

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

}
