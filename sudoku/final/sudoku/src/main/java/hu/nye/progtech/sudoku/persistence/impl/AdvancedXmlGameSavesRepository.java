package hu.nye.progtech.sudoku.persistence.impl;

import java.io.File;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.persistence.GameSavesRepository;
import hu.nye.progtech.sudoku.xml.converter.MapVOToXmlMapVOConverter;
import hu.nye.progtech.sudoku.xml.converter.XmlMapVOToMapVOConverter;
import hu.nye.progtech.sudoku.xml.model.XmlMapVO;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * XML based implementation of {@link GameSavesRepository} with a bit advanced structuring in the resulting XML output.
 */
public class AdvancedXmlGameSavesRepository implements GameSavesRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdvancedXmlGameSavesRepository.class);

    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;
    private final MapVOToXmlMapVOConverter mapVOToXmlMapVOConverter;
    private final XmlMapVOToMapVOConverter xmlMapVOToMapVOConverter;

    public AdvancedXmlGameSavesRepository(Marshaller marshaller, Unmarshaller unmarshaller,
                                          MapVOToXmlMapVOConverter mapVOToXmlMapVOConverter,
                                          XmlMapVOToMapVOConverter xmlMapVOToMapVOConverter) {
        this.marshaller = marshaller;
        this.unmarshaller = unmarshaller;
        this.mapVOToXmlMapVOConverter = mapVOToXmlMapVOConverter;
        this.xmlMapVOToMapVOConverter = xmlMapVOToMapVOConverter;
    }

    @Override
    public void save(MapVO currentMap) {
        try {
            XmlMapVO xmlMapVO = mapVOToXmlMapVOConverter.convert(currentMap);

            marshaller.marshal(xmlMapVO, new File("state.xml"));
        } catch (JAXBException e) {
            LOGGER.error("Error during saving state to XML", e);
            throw new RuntimeException("Failed to save XML", e);
        }
    }

    @Override
    public MapVO load() {
        try {
            XmlMapVO xmlMapVO = (XmlMapVO) unmarshaller.unmarshal(new File("state.xml"));

            return xmlMapVOToMapVOConverter.convert(xmlMapVO);
        } catch (JAXBException e) {
            LOGGER.error("Error during loading state to XML", e);
            throw new RuntimeException("Failed to load XML", e);
        }
    }

}
