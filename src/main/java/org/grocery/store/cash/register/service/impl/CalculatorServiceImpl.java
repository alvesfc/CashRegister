package org.grocery.store.cash.register.service.impl;

import org.grocery.store.cash.register.helper.BigDecimalHelper;
import org.grocery.store.cash.register.repository.DiscountRepository;
import org.grocery.store.cash.register.repository.entity.Coupon;
import org.grocery.store.cash.register.repository.entity.Discount;
import org.grocery.store.cash.register.repository.entity.DiscountType;
import org.grocery.store.cash.register.repository.entity.OrderDetails;
import org.grocery.store.cash.register.service.CalculatorService;
import org.grocery.store.cash.register.service.calc.Calculator;
import org.grocery.store.cash.register.service.calc.DiscountCalculator;
import org.grocery.store.cash.register.service.calc.PercentCalculator;
import org.grocery.store.cash.register.service.calc.ValueCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    private final DiscountRepository discountRepository;

    @Autowired
    public CalculatorServiceImpl(final DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public BigDecimal calculate(final OrderDetails orderDetails) {
        BigDecimal price = BigDecimalHelper.convert(orderDetails.getProduct().getPrice());

        if (orderDetails.getProduct().getDiscount() != null) {
            Discount discount = discountRepository.getOne(orderDetails.getProduct().getDiscount().getId());

            Calculator calculator;

            if (DiscountType.PERCENT.equals(discount.getType())) {
                calculator = new DiscountCalculator(discount.getRange(),
                        new PercentCalculator(new BigDecimal(discount.getValue())));
            } else {
                BigDecimal value = BigDecimalHelper.convert(discount.getValue());

                calculator = new DiscountCalculator(discount.getRange(),
                        new ValueCalculator(value));
            }

            return calculator.calculate(price, BigDecimal.valueOf(orderDetails.getQuantity()));

        }
        return price.multiply(BigDecimal.valueOf(orderDetails.getQuantity()));
    }

    @Override
    public BigDecimal calculate(final Set<Coupon> coupons, final BigDecimal amount) {
        if (coupons == null || coupons.size() == 0) {
            return amount;
        }

        BigDecimal total = BigDecimal.ZERO;
        Calculator calculator;

        for (Coupon coupon : coupons) {
            if (DiscountType.PERCENT.equals(coupon.getType())) {
                calculator = new DiscountCalculator(0,
                        new PercentCalculator(new BigDecimal(coupon.getValue())));
            } else {
                BigDecimal value = BigDecimalHelper.convert(coupon.getValue());
                calculator = new DiscountCalculator(0, new ValueCalculator(value));
            }

            BigDecimal valueWithDiscount = calculator.calculate(amount, BigDecimal.ONE);
            total = total.add(amount.subtract(valueWithDiscount));
        }

        BigDecimal totalAmount = amount.subtract(total);

        if (totalAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException("Negative value!");
        }

        return totalAmount;
    }

}
