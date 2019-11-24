package org.grocery.store.cash.register.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.grocery.store.cash.register.repository.CouponRepository;
import org.grocery.store.cash.register.repository.DiscountRepository;
import org.grocery.store.cash.register.repository.ProductRepository;
import org.grocery.store.cash.register.repository.entity.Coupon;
import org.grocery.store.cash.register.repository.entity.Discount;
import org.grocery.store.cash.register.repository.entity.DiscountType;
import org.grocery.store.cash.register.repository.entity.Product;
import org.grocery.store.cash.register.rest.resource.OrderCreate;
import org.grocery.store.cash.register.rest.resource.OrderItems;
import org.grocery.store.cash.register.rest.resource.OrderResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class OrderServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CouponRepository couponRepository;
    @Mock
    private DiscountRepository discountRepository;

    private CalculatorServiceImpl calculatorService;

    private OrderServiceImpl orderService;

    @Test
    public void testTwoProductsOneWithoutDiscount() {

        when(productRepository.getOne(1L)).thenReturn(Product
                .builder()
                .id(1L)
                .name("Product 1")
                .price(200L)
                .discount(buildDiscountTwoOneFree())
                .build());

        when(productRepository.getOne(2L)).thenReturn(Product
                .builder()
                .id(2L)
                .name("Product 2")
                .price(100L)
                .build());

        List<OrderItems> orderItems = new ArrayList<>();
        orderItems.add(buildOrderItems(1L, 5L));
        orderItems.add(buildOrderItems(2L, 2L));

        when(discountRepository.getOne(1L))
                .thenReturn(Discount.
                        builder()
                        .value(100L)
                        .range(2L)
                        .type(DiscountType.PERCENT)
                        .build());

        this.calculatorService = new CalculatorServiceImpl(discountRepository);

        this.orderService = new OrderServiceImpl(productRepository, couponRepository, calculatorService);

        OrderResponse orderResponse = this.orderService.proccess(OrderCreate.builder().orderItens(orderItems).build());

        assertEquals(new BigDecimal("10.00"), orderResponse.getAmount());

    }


    @Test
    public void testProductsWithCoupons() {

        when(productRepository.getOne(2L)).thenReturn(Product
                .builder()
                .id(2L)
                .name("Product 2")
                .price(10000L)
                .build());

        List<OrderItems> orderItems = new ArrayList<>();
        orderItems.add(buildOrderItems(2L, 2L));

        when(couponRepository.findByName("BLACK_PREVIEW_40"))
                .thenReturn(Optional.of(Coupon.builder()
                        .value(40L)
                        .threshold(10000L)
                        .description("Black Friday Preview! 40% Off $150+")
                        .name("BLACK_PREVIEW_40")
                        .type(DiscountType.PERCENT)
                        .build()));

        this.orderService = new OrderServiceImpl(productRepository, couponRepository, new CalculatorServiceImpl(discountRepository));

        Set<String> coupons = new HashSet<>();
        coupons.add("BLACK_PREVIEW_40");

        OrderResponse orderResponse = this.orderService.proccess(OrderCreate
                .builder()
                .coupons(coupons)
                .orderItens(orderItems)
                .build());

        assertEquals(new BigDecimal("120.00"), orderResponse.getAmount());

    }


    private Discount buildDiscountTwoOneFree() {
        return Discount.builder()
                .id(1L)
                .type(DiscountType.PERCENT)
                .build();
    }

    private OrderItems buildOrderItems(long code, long quantity) {
        return OrderItems.builder()
                .productCode(code)
                .quantity(quantity)
                .build();
    }

}