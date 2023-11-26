package hu.nye.progtech.sudoku.persist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LineBuilderTest {

    @Test
    void test() {
        //  given
        LineBuilder builder = new LineBuilder();
        //  when
        var result = builder.buildLines(new int[][]{
            {1,1,1,1,1,1,1,1,1},
            {2,2,2,2,2,2,2,2,2},
            {3,3,3,3,3,3,3,3,3}
        });
        //  then
        Assertions.assertArrayEquals(new String[]{
            "111111111",
            "222222222",
            "333333333"
        }, result);
    }

}
