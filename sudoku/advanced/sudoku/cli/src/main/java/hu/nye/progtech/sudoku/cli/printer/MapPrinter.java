package hu.nye.progtech.sudoku.cli.printer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hu.nye.progtech.sudoku.core.model.MapVO;
import hu.nye.progtech.sudoku.core.service.util.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Util class used to print a map.
 */
public class MapPrinter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapPrinter.class);

    private static final String HORIZONTAL_SEPARATOR = "=";
    private static final String VERTICAL_THIN_SEPARATOR = " | ";
    private static final String VERTICAL_THICK_SEPARATOR = " || ";
    private static final String LEFT_SIDE_VERTICAL_THICK_SEPARATOR = "|| ";
    private static final String RIGHT_SIDE_VERTICAL_THICK_SEPARATOR = " ||";

    private final int boxWidth;
    private final int boxHeight;
    private final MapUtil mapUtil;
    private final PrintWrapper printWrapper;

    public MapPrinter(int boxWidth, int boxHeight, MapUtil mapUtil, PrintWrapper printWrapper) {
        this.boxWidth = boxWidth;
        this.boxHeight = boxHeight;
        this.mapUtil = mapUtil;
        this.printWrapper = printWrapper;
    }

    /**
     * Prints the provided map to the standard output.
     *
     * @param mapVO the map to print
     */
    public void printMap(MapVO mapVO) {
        LOGGER.info("Printing map to stdout");
        printWrapper.printLine((getSeparator(HORIZONTAL_SEPARATOR, getSeparatorWidth(mapVO))));

        for (int rowIndex = 0; rowIndex < mapVO.getNumberOfRows(); rowIndex++) {
            String rowToPrint = getRowToPrint(mapVO, rowIndex);
            printWrapper.printLine(rowToPrint);

            if (shouldPrintHorizontalSeparator(rowIndex)) {
                printWrapper.printLine((getSeparator(HORIZONTAL_SEPARATOR, getSeparatorWidth(mapVO))));
            }
        }
    }

    private String getRowToPrint(MapVO mapVO, int rowIndex) {
        List<String> row = getRowAsStringList(mapVO, rowIndex);
        List<List<String>> boxPartsList = getBoxPartsOfRow(row);
        List<String> boxParts = joinBoxParts(boxPartsList);

        return LEFT_SIDE_VERTICAL_THICK_SEPARATOR + String.join(VERTICAL_THICK_SEPARATOR, boxParts) + RIGHT_SIDE_VERTICAL_THICK_SEPARATOR;
    }

    private List<String> getRowAsStringList(MapVO mapVO, int rowIndex) {
        return mapUtil.getRowOfMap(mapVO, rowIndex).stream()
            .map(this::valueToString)
            .collect(Collectors.toList());
    }

    private List<List<String>> getBoxPartsOfRow(List<String> row) {
        List<List<String>> boxParts = new ArrayList<>();
        List<String> currentBox = new ArrayList<>();

        for (String s : row) {
            currentBox.add(s);

            if (currentBox.size() == boxWidth) {
                boxParts.add(currentBox);
                currentBox = new ArrayList<>();
            }
        }

        if (!currentBox.isEmpty()) {
            boxParts.add(currentBox);
        }

        return boxParts;
    }

    private List<String> joinBoxParts(List<List<String>> boxPartsList) {
        return boxPartsList.stream()
            .map(strings -> String.join(VERTICAL_THIN_SEPARATOR, strings))
            .collect(Collectors.toList());
    }

    private String getSeparator(String separatorCharacter, int times) {
        List<String> separators = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            separators.add(separatorCharacter);
        }
        return String.join("", separators);
    }

    private String valueToString(int value) {
        return value == 0 ? " " : String.valueOf(value);
    }

    private boolean shouldPrintHorizontalSeparator(int rowIndex) {
        return (rowIndex + 1) % boxHeight == 0;
    }

    private int getSeparatorWidth(MapVO mapVO) {
        int numberOfBoxes = mapVO.getNumberOfColumns() / boxWidth;
        return (boxWidth * 3 + (boxWidth - 1)) * numberOfBoxes + (numberOfBoxes - 1) * 2 + 4;
    }

}
