package org.grocery.store.cash.register.service.calc;

import java.math.BigDecimal;

/**
 * Interface that defines the methods required to perform discount calculations.
 *
 * @author alvesfc
 * @version 1.0
 */
public interface Calculator {

    /**
     * @param price    - {@link BigDecimal} that represents the product price.
     * @param quantity {@link BigDecimal} that represents the number of products.
     * @return {@link BigDecimal} with the calculate discount amount.
     */
    BigDecimal calculate(final BigDecimal price, final BigDecimal quantity);
}
