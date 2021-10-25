package hu.nye.progtech;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InversionOfControlExampleTest {

    @BeforeEach
    public void setUp() {
        System.out.println("Before test case");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("After test case");
    }

    @Test
    public void testSomething() {
        System.out.println("Test case is running");
    }

}
