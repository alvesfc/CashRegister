package org.grocery.store.cash.register.service.calc;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValueCalculatorTest extends CalculatorTest {

    private Calculator oneDollarAllProducts = new ValueCalculator(new BigDecimal("1.00"));

    @Test
    void testValueCalculator() {
        BigDecimal expected = buildBigDecimal("96.00");

        BigDecimal actual = oneDollarAllProducts.calculate(new BigDecimal("25.00"), new BigDecimal(4));
        assertEquals(expected, actual);
    }

}