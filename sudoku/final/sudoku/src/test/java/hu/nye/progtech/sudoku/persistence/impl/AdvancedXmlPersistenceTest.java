package hu.nye.progtech.sudoku.persistence.impl;

import java.io.File;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.xml.converter.MapVOToXmlMapVOConverter;
import hu.nye.progtech.sudoku.xml.converter.XmlMapVOToMapVOConverter;
import hu.nye.progtech.sudoku.xml.model.XmlMapVO;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AdvancedXmlPersistenceTest {

    private AdvancedXmlPersistence underTest;

    private Marshaller marshaller;
    private Unmarshaller unmarshaller;
    private MapVOToXmlMapVOConverter mapVOToXmlMapVOConverter;
    private XmlMapVOToMapVOConverter xmlMapVOToMapVOConverter;

    @BeforeEach
    public void init() {
        marshaller = Mockito.mock(Marshaller.class);
        unmarshaller = Mockito.mock(Unmarshaller.class);
        mapVOToXmlMapVOConverter = Mockito.mock(MapVOToXmlMapVOConverter.class);
        xmlMapVOToMapVOConverter = Mockito.mock(XmlMapVOToMapVOConverter.class);
        underTest = new AdvancedXmlPersistence(marshaller, unmarshaller, mapVOToXmlMapVOConverter, xmlMapVOToMapVOConverter);
    }

    @Test
    public void testSaveShouldCallMarshallerWhenThereIsNoException() throws JAXBException {
        // Given
        MapVO currentMap = Mockito.mock(MapVO.class);
        XmlMapVO xmlMapVO = Mockito.mock(XmlMapVO.class);
        Mockito.when(mapVOToXmlMapVOConverter.convert(currentMap)).thenReturn(xmlMapVO);

        // When
        underTest.save(currentMap);

        // Then
        Mockito.verify(mapVOToXmlMapVOConverter).convert(currentMap);
        Mockito.verify(marshaller).marshal(Mockito.eq(xmlMapVO), Mockito.any(File.class));
        Mockito.verifyNoMoreInteractions(marshaller, unmarshaller, mapVOToXmlMapVOConverter, xmlMapVOToMapVOConverter, currentMap, xmlMapVO);
    }

    @Test
    public void testSaveShouldThrowRuntimeExceptionWhenThereIsJAXBException() throws JAXBException {
        // Given
        MapVO currentMap = Mockito.mock(MapVO.class);
        XmlMapVO xmlMapVO = Mockito.mock(XmlMapVO.class);
        Mockito.when(mapVOToXmlMapVOConverter.convert(currentMap)).thenReturn(xmlMapVO);
        Mockito.doThrow(JAXBException.class).when(marshaller).marshal(Mockito.eq(xmlMapVO), Mockito.any(File.class));

        // When
        Assertions.assertThrows(RuntimeException.class, () -> underTest.save(currentMap));

        // Then
        Mockito.verify(mapVOToXmlMapVOConverter).convert(currentMap);
        Mockito.verify(marshaller).marshal(Mockito.eq(xmlMapVO), Mockito.any(File.class));
        Mockito.verifyNoMoreInteractions(marshaller, unmarshaller, mapVOToXmlMapVOConverter, xmlMapVOToMapVOConverter, currentMap, xmlMapVO);
    }

    @Test
    public void testLoadShouldCallXmlMapVOToMapVOConverterWhenThereIsNoException() throws JAXBException {
        // Given
        MapVO expected = Mockito.mock(MapVO.class);
        XmlMapVO xmlMapVO = Mockito.mock(XmlMapVO.class);
        Mockito.when(unmarshaller.unmarshal(Mockito.any(File.class))).thenReturn(xmlMapVO);
        Mockito.when(xmlMapVOToMapVOConverter.convert(xmlMapVO)).thenReturn(expected);


        // When
        MapVO actual = underTest.load();

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(unmarshaller).unmarshal(Mockito.any(File.class));
        Mockito.verify(xmlMapVOToMapVOConverter).convert(xmlMapVO);
        Mockito.verifyNoMoreInteractions(marshaller, unmarshaller, mapVOToXmlMapVOConverter, xmlMapVOToMapVOConverter, xmlMapVO, expected);
    }

    @Test
    public void testLoadShouldThrowRuntimeExceptionWhenThereIsJAXBException() throws JAXBException {
        // Given
        Mockito.doThrow(JAXBException.class).when(unmarshaller).unmarshal(Mockito.any(File.class));

        // When
        Assertions.assertThrows(RuntimeException.class, () -> underTest.load());

        // Then
        Mockito.verify(unmarshaller).unmarshal(Mockito.any(File.class));
        Mockito.verifyNoMoreInteractions(marshaller, unmarshaller, mapVOToXmlMapVOConverter, xmlMapVOToMapVOConverter);
    }

}
