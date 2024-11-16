package hu.nye.progtech.sudoku.persistence.impl;

import java.io.File;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.persistence.GameSavesRepository;
import hu.nye.progtech.sudoku.persistence.xml.PersistableMapVO;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * XML based implementation of {@link GameSavesRepository}.
 */
public class XmlGameSavesRepository implements GameSavesRepository {
    protected static final File SAVE = new File("target/state.xml");
    protected final Marshaller marshaller;
    protected final Unmarshaller unmarshaller;

    public XmlGameSavesRepository(Marshaller marshaller, Unmarshaller unmarshaller) {
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
            throw new RuntimeException("Failed to save XML" + SAVE, e);
        }
    }

    @Override
    public MapVO load() {
        try {
            PersistableMapVO persistableMapVO = (PersistableMapVO) unmarshaller.unmarshal(SAVE);

            return new MapVO(persistableMapVO.getNumberOfRows(), persistableMapVO.getNumberOfColumns(),
                    persistableMapVO.getMap(), persistableMapVO.getFixed());
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to load XML" + SAVE, e);
        }
    }

}
