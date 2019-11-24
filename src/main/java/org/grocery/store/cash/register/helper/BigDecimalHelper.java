package org.grocery.store.cash.register.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalHelper {

    private static int DECIMALS = 2;
    private static BigDecimal HUNDRED = new BigDecimal("100");
    private static RoundingMode ROUNDING_MODE = RoundingMode.CEILING;

    public static BigDecimal convert(final Long value) {
        return BigDecimal.valueOf(value).divide(HUNDRED, DECIMALS, ROUNDING_MODE);
    }

}
