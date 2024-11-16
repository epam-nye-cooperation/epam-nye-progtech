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
public class AdvancedXmlGameSavesRepository extends XmlGameSavesRepository {

    private final MapVOToXmlMapVOConverter mapVOToXmlMapVOConverter;
    private final XmlMapVOToMapVOConverter xmlMapVOToMapVOConverter;

    public AdvancedXmlGameSavesRepository(Marshaller marshaller, Unmarshaller unmarshaller,
                                          MapVOToXmlMapVOConverter mapVOToXmlMapVOConverter,
                                          XmlMapVOToMapVOConverter xmlMapVOToMapVOConverter) {
        super(marshaller, unmarshaller);
        this.mapVOToXmlMapVOConverter = mapVOToXmlMapVOConverter;
        this.xmlMapVOToMapVOConverter = xmlMapVOToMapVOConverter;
    }

    @Override
    public void save(MapVO currentMap) {
        try {
            XmlMapVO xmlMapVO = mapVOToXmlMapVOConverter.convert(currentMap);

            marshaller.marshal(xmlMapVO, SAVE);
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to save XML" + SAVE, e);
        }
    }

    @Override
    public MapVO load() {
        try {
            XmlMapVO xmlMapVO = (XmlMapVO) unmarshaller.unmarshal(SAVE);

            return xmlMapVOToMapVOConverter.convert(xmlMapVO);
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to load XML " + SAVE, e);
        }
    }

}
