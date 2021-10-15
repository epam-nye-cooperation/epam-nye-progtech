package hu.nye.progtech.sudoku.service.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CollectionUtil {

    public List<Integer> getNonZeroValues(List<Integer> integerList) {
        List<Integer> result = new ArrayList<>();

        for (Integer integer : integerList) {
            if (integer != 0) {
                result.add(integer);
            }
        }

        return result;
    }

    public boolean containsOnlyDistinctValues(List<Integer> integerList) {
        Set<Integer> integerSet = Set.copyOf(integerList);
        return integerList.size() == integerSet.size();
    }

}
