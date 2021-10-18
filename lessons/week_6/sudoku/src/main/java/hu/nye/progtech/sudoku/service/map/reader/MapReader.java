package hu.nye.progtech.sudoku.service.map.reader;

import java.util.List;

import hu.nye.progtech.sudoku.service.exception.MapReadException;

public interface MapReader {

    List<String> readMap() throws MapReadException;

}
