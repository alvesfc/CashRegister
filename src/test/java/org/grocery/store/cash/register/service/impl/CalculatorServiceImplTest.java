package org.grocery.store.cash.register.service.impl;

import org.grocery.store.cash.register.repository.DiscountRepository;
import org.grocery.store.cash.register.repository.entity.Coupon;
import org.grocery.store.cash.register.repository.entity.Discount;
import org.grocery.store.cash.register.repository.entity.DiscountType;
import org.grocery.store.cash.register.repository.entity.OrderDetails;
import org.grocery.store.cash.register.repository.entity.PriceClass;
import org.grocery.store.cash.register.repository.entity.Product;
import org.grocery.store.cash.register.service.CalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CalculatorServiceImplTest {

    @Mock
    private DiscountRepository discountRepository;

    private CalculatorService calculatorService;

    @Test
    void calculatePercentDiscount() {
        this.calculatorService = new CalculatorServiceImpl(discountRepository);

        when(discountRepository.getOne(1L))
                .thenReturn(Discount.builder()
                        .id(1L)
                        .range(2L)
                        .value(50L)
                        .type(DiscountType.PERCENT)
                        .build());

        BigDecimal expected = new BigDecimal("2.50");

        BigDecimal actual = calculatorService.calculate(OrderDetails.builder()
                .quantity(3L)
                .product(Product.builder()
                        .id(1L)
                        .price(100L)
                        .name("Product 1")
                        .priceClass(PriceClass.UNIT)
                        .discount(Discount.builder()
                                .id(1L)
                                .range(2L)
                                .value(50L)
                                .type(DiscountType.PERCENT)
                                .build())
                        .build())
                .build());

        assertEquals(expected, actual);
    }

    @Test
    void calculateValueDiscount() {
        this.calculatorService = new CalculatorServiceImpl(discountRepository);

        when(discountRepository.getOne(1L))
                .thenReturn(Discount.builder()
                        .id(1L)
                        .range(2L)
                        .value(100L)
                        .type(DiscountType.VALUE)
                        .build());

        BigDecimal expected = new BigDecimal("16.97");

        BigDecimal actual = calculatorService.calculate(OrderDetails.builder()
                .quantity(3L)
                .product(Product.builder()
                        .id(1L)
                        .price(599L)
                        .name("Product 1")
                        .priceClass(PriceClass.UNIT)
                        .discount(Discount.builder()
                                .id(1L)
                                .range(2L)
                                .value(100L)
                                .type(DiscountType.VALUE)
                                .build())
                        .build())
                .build());

        assertEquals(expected, actual);
    }

    @Test
    void testCouponCalculate() {
        this.calculatorService = new CalculatorServiceImpl(discountRepository);

        Set<Coupon> coupons = new HashSet<>();
        coupons.add(Coupon.builder()
                .name("COUPON_XPTO")
                .description("40% Discount")
                .threshold(10000L)
                .type(DiscountType.PERCENT)
                .value(40L)
                .build());
        BigDecimal amount = new BigDecimal("200.00");
        BigDecimal actual = this.calculatorService.calculate(coupons, amount);
        BigDecimal expected = new BigDecimal("120.00");
        assertEquals(expected, actual);

    }

    @Test
    void testCouponCalculateWithEmptyList() {
        this.calculatorService = new CalculatorServiceImpl(discountRepository);

        Set<Coupon> coupons = new HashSet<>();

        BigDecimal amount = new BigDecimal("200.00");
        BigDecimal actual = this.calculatorService.calculate(coupons, amount);
        BigDecimal expected = new BigDecimal("200.00");
        assertEquals(expected, actual);

    }

    @Test
    void testCouponCalculateWithNullList() {
        this.calculatorService = new CalculatorServiceImpl(discountRepository);

        BigDecimal amount = new BigDecimal("200.00");
        BigDecimal actual = this.calculatorService.calculate(null, amount);
        BigDecimal expected = new BigDecimal("200.00");
        assertEquals(expected, actual);
    }

    @Test
    void testMoreThanOneCouponCalculate() {
        this.calculatorService = new CalculatorServiceImpl(discountRepository);

        Set<Coupon> coupons = new HashSet<>();
        coupons.add(Coupon.builder()
                .name("COUPON_XPTO")
                .description("40% Discount")
                .threshold(10000L)
                .type(DiscountType.PERCENT)
                .value(40L)
                .build());

        coupons.add(Coupon.builder()
                .name("COUPON_ABC")
                .description("$10 Discount")
                .threshold(10000L)
                .type(DiscountType.VALUE)
                .value(1000L)
                .build());

        BigDecimal amount = new BigDecimal("200.00");
        BigDecimal actual = this.calculatorService.calculate(coupons, amount);
        BigDecimal expected = new BigDecimal("110.00");

        assertEquals(expected, actual);
    }

    @Test()
    void whenTotalAmountLessThanZeroThrowException() {
        this.calculatorService = new CalculatorServiceImpl(discountRepository);

        Set<Coupon> coupons = new HashSet<>();
        coupons.add(Coupon.builder()
                .name("COUPON_XPTO")
                .description("40% Discount")
                .threshold(10000L)
                .type(DiscountType.PERCENT)
                .value(60L)
                .build());

        coupons.add(Coupon.builder()
                .name("COUPON_ABC")
                .description("$10 Discount")
                .threshold(10000L)
                .type(DiscountType.VALUE)
                .value(6000L)
                .build());

        BigDecimal amount = new BigDecimal("100.00");

        Assertions.assertThrows(IllegalStateException.class, () -> {
            this.calculatorService.calculate(coupons, amount);
        });
    }
}