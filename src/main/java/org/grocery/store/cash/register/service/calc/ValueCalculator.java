package org.grocery.store.cash.register.service.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class responsible for calculating the monetary discount.
 *
 * @author alvesfc
 * @version 1.0
 */
public class ValueCalculator implements Calculator {

    private static int DECIMALS = 3;
    private static RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

    private final BigDecimal value;

    public ValueCalculator(final BigDecimal value) {
        this.value = value;
    }

    @Override
    public BigDecimal calculate(final BigDecimal price, final BigDecimal quantity) {
        return getDifference(price).multiply(quantity).setScale(2, ROUNDING_MODE);
    }

    private BigDecimal getDifference(final BigDecimal price) {
        return price.subtract(this.value);
    }
}
