package hu.nye.progtech.sudoku.persistence.xml.repository;

import java.io.File;

import hu.nye.progtech.sudoku.core.model.MapVO;
import hu.nye.progtech.sudoku.core.persistence.api.GameSavesRepository;
import hu.nye.progtech.sudoku.persistence.xml.converter.MapVOToXmlMapVOConverter;
import hu.nye.progtech.sudoku.persistence.xml.converter.XmlMapVOToMapVOConverter;
import hu.nye.progtech.sudoku.persistence.xml.model.XmlMapVO;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * XML based implementation of {@link GameSavesRepository} with a bit advanced structuring in the resulting XML output.
 */
public class XmlGameSavesRepository implements GameSavesRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlGameSavesRepository.class);

    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;
    private final MapVOToXmlMapVOConverter mapVOToXmlMapVOConverter;
    private final XmlMapVOToMapVOConverter xmlMapVOToMapVOConverter;

    public XmlGameSavesRepository(Marshaller marshaller, Unmarshaller unmarshaller,
                                  MapVOToXmlMapVOConverter mapVOToXmlMapVOConverter,
                                  XmlMapVOToMapVOConverter xmlMapVOToMapVOConverter) {
        this.marshaller = marshaller;
        this.unmarshaller = unmarshaller;
        this.mapVOToXmlMapVOConverter = mapVOToXmlMapVOConverter;
        this.xmlMapVOToMapVOConverter = xmlMapVOToMapVOConverter;
    }

    @Override
    public void save(String username, MapVO currentMap) {
        try {
            XmlMapVO xmlMapVO = mapVOToXmlMapVOConverter.convert(currentMap);

            marshaller.marshal(xmlMapVO, new File(username + "_state.xml"));
        } catch (JAXBException e) {
            LOGGER.error("Error during saving state to XML", e);
            throw new RuntimeException("Failed to save XML", e);
        }
    }

    @Override
    public MapVO load(String username) {
        try {
            XmlMapVO xmlMapVO = (XmlMapVO) unmarshaller.unmarshal(new File(username + "_state.xml"));

            return xmlMapVOToMapVOConverter.convert(xmlMapVO);
        } catch (JAXBException e) {
            LOGGER.error("Error during loading state to XML", e);
            throw new RuntimeException("Failed to load XML", e);
        }
    }

}
