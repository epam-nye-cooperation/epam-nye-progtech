package hu.nye.progtech.sudoku;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import hu.nye.progtech.sudoku.model.Grid;
import hu.nye.progtech.sudoku.model.GridLine;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class Main {

    public static void main(String[] args) throws JAXBException, URISyntaxException, SAXException {
        /*
        Marshalling (XML Java objektumokból)
         */
        List<GridLine> gridLines = List.of(
            new GridLine("401290075", 1, 9, false),
            new GridLine("200300800", 2, 9, false),
            new GridLine("070080006", 3, 9, false),
            new GridLine("000103062", 4, 9, false),
            new GridLine("105000403", 5, 9, false),
            new GridLine("730608000", 6, 9, false),
            new GridLine("600020030", 7, 9, false),
            new GridLine("007001004", 8, 9, false),
            new GridLine("890065107", 9, 9, false)
        );
        Grid grid = new Grid("beginner", 9, 9, false, gridLines);
        JAXBContext context = JAXBContext.newInstance(Grid.class, GridLine.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "c:/tmp/prog/schema1.xsd");;
        marshaller.marshal(grid, new File("./beginner.xml"));

        /*
        Unmarshalling (Java objektumok XML-ből)
         */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema gridSchema = sf.newSchema(new File("c:/tmp/prog/schema1.xsd"));
        unmarshaller.setSchema(gridSchema);
        grid = (Grid)unmarshaller.unmarshal(new File("./beginner.xml"));
        System.out.println(grid.getGridLines());

        /*
        jaxb2-maven-plugin

        1.a. Schema generálás Java osztályokból:
        schemagen execution engedélyezése

        1.b. Java osztályok generálása sémából:
        xjc execution engedélyezése

        2. Generálás: mvn package
         */

    }

}









