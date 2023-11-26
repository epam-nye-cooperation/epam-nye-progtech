package hu.nye.progtech.sudoku.persist.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hu.nye.progtech.sudoku.exceptions.GridReadException;
import hu.nye.progtech.sudoku.exceptions.GridWriteException;
import hu.nye.progtech.sudoku.exceptions.InvalidGridException;
import hu.nye.progtech.sudoku.persist.LineParserImpl;
import hu.nye.progtech.sudoku.persist.Repository;
import hu.nye.progtech.sudoku.persist.db.DbPersister;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class XmlRepository implements Repository {

    private final JAXBContext context;

    public XmlRepository() {
        try {
            context = JAXBContext.newInstance(GridXml.class, GridLineXml.class);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override public String[] readLines() throws GridReadException {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            GridXml grid = (GridXml) unmarshaller.unmarshal(
                new File(DbPersister.class.getClassLoader().getResource("beginner.xml").getPath()));
            String[] lines = new String[grid.getGridLines().size()];
            for (GridLineXml line : grid.getGridLines()) {
                lines[line.getRowNumber() - 1] = line.getLine();
            }
            return lines;
        } catch (JAXBException e) {
            throw new GridReadException(e.getMessage());
        }
    }

    @Override public void writeLines(String[] lines) throws GridWriteException {
        String name = "beginner_%s";
        GridXml grid = new GridXml(String.format(name, new Date().getTime()),
            lines.length, lines[0].length(), false, getGridLines(lines));
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            String path = this.getClass().getClassLoader().getResource(".").getPath() +
                "/" + String.format("beginner_%s.xml", new Date().getTime());
            marshaller.marshal(grid, new File(path));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private List<GridLineXml> getGridLines(String[] lines) {
        List<GridLineXml> gridLines = new ArrayList<>(lines.length);
        for (int i = 0; i < lines.length; i++) {
            gridLines.add(new GridLineXml(lines[i], i + 1, lines[i].length(), false));
        }
        return gridLines;
    }

}
