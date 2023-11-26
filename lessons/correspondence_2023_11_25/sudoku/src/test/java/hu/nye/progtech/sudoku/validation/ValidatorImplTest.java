package hu.nye.progtech.sudoku.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import hu.nye.progtech.sudoku.exceptions.InvalidGridException;
import hu.nye.progtech.sudoku.model.Grid;

class ValidatorImplTest {

    @Test
    void testRowValidation() {
        //  given
        int[][] cells = {
            {1,1,1,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
        };
        //  when
        var exception = Assertions.assertThrows(InvalidGridException.class, () -> new ValidatorImpl().validate(new Grid(cells)));
        //  then
        Assertions.assertNotNull(exception);
    }

    @Test
    void testColumnValidation() {
        //  given
        int[][] cells = {
            {1,0,0,0,0,0,0,0,0},
            {1,0,0,0,0,0,0,0,0},
            {1,0,0,0,0,0,0,0,0},
            {1,0,0,0,0,0,0,0,0},
            {1,0,0,0,0,0,0,0,0},
            {1,0,0,0,0,0,0,0,0},
            {1,0,0,0,0,0,0,0,0},
            {1,0,0,0,0,0,0,0,0},
            {1,0,0,0,0,0,0,0,0}
        };
        //  when
        var exception = Assertions.assertThrows(InvalidGridException.class, () -> new ValidatorImpl().validate(new Grid(cells)));
        //  then
        Assertions.assertNotNull(exception);
    }

    @Test
    void testBoxValidation() {
        //  given
        int[][] cells = {
            {1,0,0,0,0,0,0,0,0},
            {0,1,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
        };
        //  when
        var exception = Assertions.assertThrows(InvalidGridException.class, () -> new ValidatorImpl().validate(new Grid(cells)));
        //  then
        Assertions.assertNotNull(exception);
    }

}
