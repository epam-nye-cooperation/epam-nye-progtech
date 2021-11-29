package hu.nye.progtech.sudoku.persistence.impl;

import java.io.File;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.persistence.GameSavesRepository;
import hu.nye.progtech.sudoku.persistence.xml.PersistableMapVO;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

/**
 * XML based implementation of {@link GameSavesRepository}.
 */
public class XmlGameSavesRepository implements GameSavesRepository {

    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    public XmlGameSavesRepository(Marshaller marshaller, Unmarshaller unmarshaller) {
        this.marshaller = marshaller;
        this.unmarshaller = unmarshaller;
    }

    @Override
    public void save(MapVO currentMap) {
        try {
            PersistableMapVO persistableMapVO =
                new PersistableMapVO(currentMap.getNumberOfRows(), currentMap.getNumberOfColumns(), currentMap.getMap(), currentMap.getFixed());

            marshaller.marshal(persistableMapVO, new File("state.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MapVO load() {
        try {
            PersistableMapVO persistableMapVO = (PersistableMapVO) unmarshaller.unmarshal(new File("state.xml"));

            return new MapVO(persistableMapVO.getNumberOfRows(), persistableMapVO.getNumberOfColumns(), persistableMapVO.getMap(), persistableMapVO.getFixed());
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load XML");
        }
    }

}
