package org.grocery.store.cash.register.service.calc;

import java.math.BigDecimal;

/**
 * Class responsible for calculating the discount according to the given parameters.
 *
 * @author alvesfc
 * @version 1.0
 */
public class DiscountCalculator implements Calculator {

    private static int DECIMALS = 2;
    private final long sizeUntilDiscount;
    private final Calculator calculator;

    public DiscountCalculator(final long sizeUntilDiscount, final Calculator calculator) {
        this.sizeUntilDiscount = sizeUntilDiscount;
        this.calculator = calculator;
    }

    @Override
    public BigDecimal calculate(final BigDecimal price, final BigDecimal quantity) {
        BigDecimal fullPrice = BigDecimal.ZERO;

        if (this.sizeUntilDiscount != 0) {
            fullPrice = getBigDecimal(quantity);
        }
        BigDecimal discountPrice = quantity.subtract(fullPrice);

        BigDecimal totalFullPrice = price.multiply(fullPrice);
        BigDecimal totalDiscount = calculator.calculate(price, discountPrice);

        return totalFullPrice.add(totalDiscount);
    }

    private BigDecimal getBigDecimal(BigDecimal quantity) {
        BigDecimal value;

        if (this.sizeUntilDiscount == 1) {
            value = quantity.divide(new BigDecimal("2.00"), DECIMALS, BigDecimal.ROUND_UP).setScale(0, BigDecimal.ROUND_CEILING);
        } else {
            value = quantity.divide(new BigDecimal(this.sizeUntilDiscount), DECIMALS, BigDecimal.ROUND_DOWN).setScale(0, BigDecimal.ROUND_DOWN);
        }
        return value.multiply(new BigDecimal(this.sizeUntilDiscount));
    }

}
