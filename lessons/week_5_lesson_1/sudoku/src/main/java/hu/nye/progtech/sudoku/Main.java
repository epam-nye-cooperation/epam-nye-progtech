package hu.nye.progtech.sudoku;

import hu.nye.progtech.sudoku.model.MapVO;
import hu.nye.progtech.sudoku.service.exception.MapReadException;
import hu.nye.progtech.sudoku.service.map.parser.MapParser;
import hu.nye.progtech.sudoku.service.map.reader.MapReader;
import hu.nye.progtech.sudoku.service.map.reader.impl.BufferedReaderMapReader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int[][] map = {
            {0, 1},
            {2, 3}
        };
        boolean[][] fixed = {
            {false, true},
            {true, true}
        };
        MapVO mapVO = new MapVO(2, 2, map, fixed);

        System.out.println(mapVO);


        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("map/beginner.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        MapReader mapReader = new BufferedReaderMapReader(bufferedReader);
        try {
            List<String> strings = mapReader.readMap();
            System.out.println(strings);

            MapParser mapParser = new MapParser(9, 9);
            MapVO mapVO1 = mapParser.parseMap(strings);
            System.out.println(mapVO1);
        } catch (MapReadException e) {
            e.printStackTrace();
        }

    }

}
