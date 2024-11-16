package hu.nye.progtech.sudoku.persistence.impl;

import java.io.File;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.persistence.xml.PersistableMapVO;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class XmlPersistenceTest {

    private XmlPersistence underTest;

    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    @BeforeEach
    public void init() {
        marshaller = Mockito.mock(Marshaller.class);
        unmarshaller = Mockito.mock(Unmarshaller.class);
        underTest = new XmlPersistence(marshaller, unmarshaller);
    }

    @Test
    public void testSaveShouldCallMarshallerWhenThereIsNoException() throws JAXBException {
        // Given
        MapVO currentMap = Mockito.mock(MapVO.class);
        int numberOfRows = 9;
        Mockito.when(currentMap.getNumberOfRows()).thenReturn(numberOfRows);
        int numberOfColumns = 8;
        Mockito.when(currentMap.getNumberOfColumns()).thenReturn(numberOfColumns);
        int[][] map = {{1,2}, {3,4}};
        Mockito.when(currentMap.getMap()).thenReturn(map);
        boolean[][] fixed = {{true, false}, {false, true}};
        Mockito.when(currentMap.getFixed()).thenReturn(fixed);

        // When
        underTest.save(currentMap);

        // Then
        PersistableMapVO persistableMapVO = new PersistableMapVO(numberOfRows, numberOfColumns, map, fixed);
        Mockito.verify(marshaller).marshal(Mockito.eq(persistableMapVO), Mockito.any(File.class));
        Mockito.verify(currentMap).getNumberOfRows();
        Mockito.verify(currentMap).getNumberOfColumns();
        Mockito.verify(currentMap).getMap();
        Mockito.verify(currentMap).getFixed();
        Mockito.verifyNoMoreInteractions(marshaller, unmarshaller, currentMap);
    }

    @Test
    public void testSaveShouldThrowRuntimeExceptionWhenThereIsJAXBException() throws JAXBException {
        // Given
        MapVO currentMap = Mockito.mock(MapVO.class);
        int numberOfRows = 9;
        Mockito.when(currentMap.getNumberOfRows()).thenReturn(numberOfRows);
        int numberOfColumns = 8;
        Mockito.when(currentMap.getNumberOfColumns()).thenReturn(numberOfColumns);
        int[][] map = {{1,2}, {3,4}};
        Mockito.when(currentMap.getMap()).thenReturn(map);
        boolean[][] fixed = {{true, false}, {false, true}};
        Mockito.when(currentMap.getFixed()).thenReturn(fixed);
        PersistableMapVO persistableMapVO = new PersistableMapVO(numberOfRows, numberOfColumns, map, fixed);
        Mockito.doThrow(JAXBException.class).when(marshaller).marshal(Mockito.eq(persistableMapVO), Mockito.any(File.class));

        // When
        Assertions.assertThrows(RuntimeException.class, () -> underTest.save(currentMap));

        // Then
        Mockito.verify(marshaller).marshal(Mockito.eq(persistableMapVO), Mockito.any(File.class));
        Mockito.verify(currentMap).getNumberOfRows();
        Mockito.verify(currentMap).getNumberOfColumns();
        Mockito.verify(currentMap).getMap();
        Mockito.verify(currentMap).getFixed();
        Mockito.verifyNoMoreInteractions(marshaller, unmarshaller, currentMap);
    }

    @Test
    public void testLoadShouldCallXmlMapVOToMapVOConverterWhenThereIsNoException() throws JAXBException {
        // Given
        PersistableMapVO persistableMapVO = Mockito.mock(PersistableMapVO.class);
        int numberOfRows = 9;
        Mockito.when(persistableMapVO.getNumberOfRows()).thenReturn(numberOfRows);
        int numberOfColumns = 8;
        Mockito.when(persistableMapVO.getNumberOfColumns()).thenReturn(numberOfColumns);
        int[][] map = {{1,2}, {3,4}};
        Mockito.when(persistableMapVO.getMap()).thenReturn(map);
        boolean[][] fixed = {{true, false}, {false, true}};
        Mockito.when(persistableMapVO.getFixed()).thenReturn(fixed);
        MapVO expected  = new MapVO(numberOfRows, numberOfColumns, map, fixed);
        Mockito.when(unmarshaller.unmarshal(Mockito.any(File.class))).thenReturn(persistableMapVO);

        // When
        MapVO actual = underTest.load();

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(unmarshaller).unmarshal(Mockito.any(File.class));
        Mockito.verify(persistableMapVO).getNumberOfRows();
        Mockito.verify(persistableMapVO).getNumberOfColumns();
        Mockito.verify(persistableMapVO).getMap();
        Mockito.verify(persistableMapVO).getFixed();
        Mockito.verifyNoMoreInteractions(marshaller, unmarshaller, persistableMapVO);
    }

    @Test
    public void testLoadShouldThrowRuntimeExceptionWhenThereIsJAXBException() throws JAXBException {
        // Given
        PersistableMapVO persistableMapVO = Mockito.mock(PersistableMapVO.class);
        int numberOfRows = 9;
        Mockito.when(persistableMapVO.getNumberOfRows()).thenReturn(numberOfRows);
        int numberOfColumns = 8;
        Mockito.when(persistableMapVO.getNumberOfColumns()).thenReturn(numberOfColumns);
        int[][] map = {{1,2}, {3,4}};
        Mockito.when(persistableMapVO.getMap()).thenReturn(map);
        boolean[][] fixed = {{true, false}, {false, true}};
        Mockito.when(persistableMapVO.getFixed()).thenReturn(fixed);
        Mockito.when(unmarshaller.unmarshal(Mockito.any(File.class))).thenThrow(JAXBException.class);

        // When
        Assertions.assertThrows(RuntimeException.class, () -> underTest.load());

        // Then
        Mockito.verify(unmarshaller).unmarshal(Mockito.any(File.class));
        Mockito.verifyNoMoreInteractions(marshaller, unmarshaller, persistableMapVO);
    }

}
