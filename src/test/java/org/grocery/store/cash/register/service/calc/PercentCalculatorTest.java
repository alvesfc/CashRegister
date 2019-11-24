package org.grocery.store.cash.register.service.calc;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PercentCalculatorTest extends CalculatorTest {

    private Calculator tenPercent = new PercentCalculator(new BigDecimal("10.00"));
    private Calculator roundUp = new PercentCalculator(new BigDecimal("50.00"));

    @Test
    void testPercentDiscount() {
        BigDecimal expected = buildBigDecimal("90.00");

        BigDecimal actual = tenPercent.calculate(new BigDecimal("25.00"), new BigDecimal(4));
        assertEquals(expected, actual);
    }

    @Test
    void testDecimalRoundUp() {
        BigDecimal expected = buildBigDecimal("1.48");

        BigDecimal actual = roundUp.calculate(new BigDecimal("0.99"), new BigDecimal(3));
        assertEquals(expected, actual);
    }


}