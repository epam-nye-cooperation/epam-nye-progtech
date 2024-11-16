package hu.nye.progtech.sudoku.persistence.impl;

import java.io.File;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.persistence.Persistence;
import hu.nye.progtech.sudoku.persistence.xml.PersistableMapVO;
import hu.nye.progtech.sudoku.service.exception.MapSavingException;
import hu.nye.progtech.sudoku.service.exception.MapReadingException;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

/**
 * XML based implementation of {@link Persistence}.
 */
public class XmlPersistence implements Persistence {
    protected static final File SAVE = new File("target/state.xml");
    protected final Marshaller marshaller;
    protected final Unmarshaller unmarshaller;

    public XmlPersistence(Marshaller marshaller, Unmarshaller unmarshaller) {
        this.marshaller = marshaller;
        this.unmarshaller = unmarshaller;
    }

    @Override
    public void save(MapVO currentMap) {
        try {
            PersistableMapVO persistableMapVO =
                new PersistableMapVO(currentMap.getNumberOfRows(), currentMap.getNumberOfColumns(), currentMap.getMap(),
                        currentMap.getFixed());

            marshaller.marshal(persistableMapVO, SAVE);
        } catch (JAXBException e) {
            throw new MapSavingException("Failed to save XML" + SAVE, e);
        }
    }

    @Override
    public MapVO load() {
        try {
            PersistableMapVO persistableMapVO = (PersistableMapVO) unmarshaller.unmarshal(SAVE);

            return new MapVO(persistableMapVO.getNumberOfRows(), persistableMapVO.getNumberOfColumns(),
                    persistableMapVO.getMap(), persistableMapVO.getFixed());
        } catch (JAXBException e) {
            throw new MapReadingException("Failed to load XML" + SAVE, e);
        }
    }

}
