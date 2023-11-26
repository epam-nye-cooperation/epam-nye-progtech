package hu.nye.progtech.sudoku.persist.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hu.nye.progtech.sudoku.exceptions.GridReadException;
import hu.nye.progtech.sudoku.exceptions.GridWriteException;
import hu.nye.progtech.sudoku.exceptions.InvalidGridException;
import hu.nye.progtech.sudoku.persist.AbstractPersister;
import hu.nye.progtech.sudoku.persist.LineParserImpl;
import hu.nye.progtech.sudoku.persist.Persister;
import hu.nye.progtech.sudoku.persist.Repository;
import hu.nye.progtech.sudoku.persist.db.DbPersister;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class XmlPersister extends AbstractPersister {

    private final XmlRepository xmlRepository = new XmlRepository();

    @Override protected Repository getRepository() {
        return xmlRepository;
    }
}
