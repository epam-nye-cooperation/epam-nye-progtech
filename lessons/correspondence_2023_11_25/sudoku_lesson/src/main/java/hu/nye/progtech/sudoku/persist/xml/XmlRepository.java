package hu.nye.progtech.sudoku.persist.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hu.nye.progtech.sudoku.exceptions.GridReadException;
import hu.nye.progtech.sudoku.exceptions.GridWriteException;
import hu.nye.progtech.sudoku.persist.Repository;
import hu.nye.progtech.sudoku.persist.xml.model.GridLineXml;
import hu.nye.progtech.sudoku.persist.xml.model.GridXml;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class XmlRepository implements Repository {

    @Override public String[] readLines() throws GridReadException {
        try {
            JAXBContext context = JAXBContext.newInstance(GridXml.class, GridLineXml.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            GridXml grid = (GridXml) unmarshaller.unmarshal(
                new File(XmlRepository.class.getClassLoader().getResource("beginner.xml").getPath()));
            String[] lines = new String[grid.getLines().size()];
            for (GridLineXml line : grid.getLines()) {
                lines[line.getRowNumber() - 1] = line.getLine();
            }
            return lines;
        } catch (JAXBException e) {
            throw new GridReadException(e.getMessage());
        }
    }

    @Override public void writeLines(String[] lines) throws GridWriteException {
        String name = String.format("beginner_%s", new Date().getTime());
        GridXml grid = new GridXml(name, lines.length, lines[0].length(), false, getGridLines(lines));
        try {
            JAXBContext context = JAXBContext.newInstance(GridXml.class, GridLineXml.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            String path = XmlRepository.class.getClassLoader().getResource(".").getPath() +
                "/" + String.format("beginner_%s.xml", new Date().getTime());
            marshaller.marshal(grid, new File(path));
        } catch (JAXBException e) {
            throw new GridWriteException(e.getMessage());
        }

    }

    private List<GridLineXml> getGridLines(String[] lines) {
        List<GridLineXml> gridLines = new ArrayList<>(lines.length);
        for (int i = 0; i < lines.length; i++) {
            gridLines.add(new GridLineXml(lines[i].length(), lines[i], i));

        }
        return gridLines;
    }

}
