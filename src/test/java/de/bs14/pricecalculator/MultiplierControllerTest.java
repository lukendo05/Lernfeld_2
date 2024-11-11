package de.bs14.pricecalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerTest {

    public static final String TEST_NAME = "testName";
    private final Controller controller = new Controller();

    @BeforeEach
    void setUp(){
        controller.login(TEST_NAME);
    }

    @Test
    void testMultiplikationStandard(){
        String result = controller.multiply(5, BigDecimal.TWO);
        assertEquals("10.00", result);
    }

    @Test
    void testMultiplikationFactorNull(){
        String result = controller.multiply(5, BigDecimal.ZERO);
        assertEquals("0.00", result);
    }

    @Test
    void testMultiplikationNumberNull(){
        String result = controller.multiply(0, BigDecimal.TWO);
        assertEquals("0.00", result);
    }

    @Test
    void testGetCalculationsDefault() {
        List<Calculation> result = controller.getCalculations(TEST_NAME);
        assertEquals(0, result.size());
    }

    @Test
    void testCalculate() {
        Calculation result = controller.calculate(TEST_NAME, 2, BigDecimal.TWO);
        assertEquals("4.00", result.result());
    }

    @Test
    void testGetCalculations() {
        controller.calculate(TEST_NAME, 2, BigDecimal.TWO);
        List<Calculation> result = controller.getCalculations(TEST_NAME);
        assertEquals("4.00", result.getFirst().result());
    }
}
