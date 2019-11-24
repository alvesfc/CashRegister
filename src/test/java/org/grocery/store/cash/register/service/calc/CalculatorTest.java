package org.grocery.store.cash.register.service.calc;

import java.math.BigDecimal;

public abstract class CalculatorTest {

    private static final int SCALE = 2;

    protected BigDecimal buildBigDecimal(String value) {
        BigDecimal expected = new BigDecimal(value);
        expected.setScale(SCALE, BigDecimal.ROUND_DOWN);
        return expected;
    }
}
