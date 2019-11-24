package org.grocery.store.cash.register;

import org.grocery.store.cash.register.helper.BigDecimalHelper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BigDecimalHelperTest {


    @Test
    public void testConvertLong() {
        BigDecimal actual = BigDecimalHelper.convert(599L);

        BigDecimal expected = new BigDecimal("5.99");

        assertEquals(expected, actual);

    }

}