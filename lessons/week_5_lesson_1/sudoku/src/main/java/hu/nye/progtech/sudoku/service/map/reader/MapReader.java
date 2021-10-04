package hu.nye.progtech.sudoku.service.map.reader;

import hu.nye.progtech.sudoku.service.exception.MapReadException;

import java.util.List;

public interface MapReader {

    List<String> readMap() throws MapReadException;

}
