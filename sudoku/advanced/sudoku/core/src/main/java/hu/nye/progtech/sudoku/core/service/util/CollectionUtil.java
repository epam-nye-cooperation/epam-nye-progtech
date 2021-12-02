package hu.nye.progtech.sudoku.core.service.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Util class containing useful collection related operations.
 */
public class CollectionUtil {

    /**
     * Collects all the non-zero values from an integer list.
     *
     * @param integerList the list to filter zero values from
     * @return a new list containing only non-zero values
     */
    public List<Integer> collectNonZeroValues(List<Integer> integerList) {
        List<Integer> result = new ArrayList<>();

        for (int value : integerList) {
            if (value != 0) {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * Checks if a given list only contains distinct values.
     *
     * @param integerList the integer list to check
     * @return {@code true} if the list only contains distinct values, {@code false} otherwise
     */
    public boolean containsOnlyDistinctValues(List<Integer> integerList) {
        Set<Integer> distinctValues = Set.copyOf(integerList);
        return integerList.size() == distinctValues.size();
    }

}
