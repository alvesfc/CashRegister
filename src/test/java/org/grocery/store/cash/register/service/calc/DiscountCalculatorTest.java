package org.grocery.store.cash.register.service.calc;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountCalculatorTest extends CalculatorTest {

    private DiscountCalculator buyTwoGetThirthWithFifityPercent = new DiscountCalculator(2, new PercentCalculator(new BigDecimal("50.00")));
    private DiscountCalculator buyOneGetSecondFree = new DiscountCalculator(1, new PercentCalculator(new BigDecimal("100.00")));
    private DiscountCalculator buyOneGetSecondWith30Percent = new DiscountCalculator(1, new PercentCalculator(new BigDecimal("30.00")));

    @Test
    void whenReceiveFiveItemsApplyDiscountInTwo() {
        BigDecimal expected = buildBigDecimal("11.25");

        BigDecimal actual = buyTwoGetThirthWithFifityPercent.calculate(new BigDecimal("2.50"), new BigDecimal(5));
        assertEquals(expected, actual);
    }

    @Test
    void whenThePriceHasDecimalRoundUp() {
        BigDecimal expected = buildBigDecimal("2.48");

        BigDecimal actual = buyTwoGetThirthWithFifityPercent.calculate(new BigDecimal("0.99"), new BigDecimal(3));
        assertEquals(expected, actual);
    }

    @Test
    void testBuyOneGetSecondFree() {
        BigDecimal expected = buildBigDecimal("1.98");

        BigDecimal actual = buyOneGetSecondFree.calculate(new BigDecimal("0.99"), new BigDecimal(3));
        assertEquals(expected, actual);
    }

    @Test
    void testBuyOneGetSecondWith30Percent() {
        BigDecimal expected = buildBigDecimal("27.00");

        BigDecimal actual = buyOneGetSecondWith30Percent.calculate(new BigDecimal("10.00"), new BigDecimal(3));
        assertEquals(expected, actual);
    }
}