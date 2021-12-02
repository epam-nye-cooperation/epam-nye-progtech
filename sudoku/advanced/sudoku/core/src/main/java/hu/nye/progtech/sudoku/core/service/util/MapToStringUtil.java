package hu.nye.progtech.sudoku.core.service.util;

import hu.nye.progtech.sudoku.core.model.MapVO;

/**
 * Util class containing useful operations for serialize a {@link MapVO}.
 */
public class MapToStringUtil {

    /**
     * Convert {@link MapVO}'s map attribute to String.
     *
     * @param mapVO the actual {@link MapVO} to be serialized
     * @return {@link String} representation of actual {@link MapVO}'s {@code map} attribute
     */
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

    /**
     * Convert {@link MapVO}'s fixed attribute to String.
     *
     * @param mapVO the actual {@link MapVO} to be serialized
     * @return {@link String} representation of actual {@link MapVO}'s {@code fixed}  attribute
     */
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
