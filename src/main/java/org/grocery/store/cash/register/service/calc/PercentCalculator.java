package org.grocery.store.cash.register.service.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class responsible for calculating the percentual discount.
 *
 * @author alvesfc
 * @version 1.0
 */
public class PercentCalculator implements Calculator {

    private static int DECIMALS = 3;
    private static BigDecimal HUNDRED = new BigDecimal("100");
    private static RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

    private final BigDecimal percentage;

    public PercentCalculator(final BigDecimal percentage) {
        this.percentage = percentage;
    }

    @Override
    public BigDecimal calculate(final BigDecimal price, final BigDecimal quantity) {
        return getPercentage(price).multiply(quantity).setScale(2, ROUNDING_MODE);
    }


    private BigDecimal getPercentage(final BigDecimal price) {
        BigDecimal result = price.multiply(this.percentage);
        result = result.divide(HUNDRED, DECIMALS, ROUNDING_MODE);
        return rounded(price.subtract(result));
    }


    private BigDecimal rounded(BigDecimal number) {
        return number.setScale(DECIMALS, ROUNDING_MODE);
    }

}
