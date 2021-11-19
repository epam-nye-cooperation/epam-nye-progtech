package hu.nye.progtech.sudoku.service.util;

import hu.nye.progtech.sudoku.model.MapVO;

public class MapToStringUtil {

    public String convertMapVoMapToString(MapVO mapVO) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < mapVO.getNumberOfRows(); i++) {
            for (int j = 0; j < mapVO.getNumberOfColumns(); j++) {
                int number = mapVO.getMap()[i][j];
                builder.append(number);
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    public String convertMapVoFixedToString(MapVO mapVO) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < mapVO.getNumberOfRows(); i++) {
            for (int j = 0; j < mapVO.getNumberOfColumns(); j++) {
                boolean fixedValue = mapVO.getFixed()[i][j];
                String fixedValueAsString = fixedValue ? "1" : "0";
                builder.append(fixedValueAsString);
            }
            builder.append("\n");
        }

        return builder.toString();
    }

}
