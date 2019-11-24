package org.grocery.store.cash.register.service;

import org.grocery.store.cash.register.repository.entity.Coupon;
import org.grocery.store.cash.register.repository.entity.OrderDetails;

import java.math.BigDecimal;
import java.util.Set;

public interface CalculatorService {

    BigDecimal calculate(final OrderDetails orderDetails);

    BigDecimal calculate(final Set<Coupon> coupons, final BigDecimal amount);

}
