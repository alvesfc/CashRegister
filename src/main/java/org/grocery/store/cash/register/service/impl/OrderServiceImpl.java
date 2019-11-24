package org.grocery.store.cash.register.service.impl;

import org.grocery.store.cash.register.repository.CouponRepository;
import org.grocery.store.cash.register.repository.ProductRepository;
import org.grocery.store.cash.register.repository.entity.Coupon;
import org.grocery.store.cash.register.repository.entity.OrderDetails;
import org.grocery.store.cash.register.rest.resource.OrderCreate;
import org.grocery.store.cash.register.rest.resource.OrderResponse;
import org.grocery.store.cash.register.service.CalculatorService;
import org.grocery.store.cash.register.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;

    private final CouponRepository couponRepository;

    private final CalculatorService calculatorService;

    @Autowired
    public OrderServiceImpl(ProductRepository productRepository,
                            CouponRepository couponRepository,
                            CalculatorService calculatorService) {
        this.productRepository = productRepository;
        this.couponRepository = couponRepository;
        this.calculatorService = calculatorService;
    }

    public OrderResponse proccess(final OrderCreate orderCreate) {

        List<OrderDetails> orderDetails = orderCreate.getOrderItens().stream()
                .map(orderItems -> OrderDetails.builder()
                        .product(productRepository.getOne(orderItems.getProductCode()))
                        .quantity(orderItems.getQuantity())
                        .build())
                .collect(Collectors.toList());

        BigDecimal totalAmount = orderDetails.stream()
                .map(calculatorService::calculate)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (orderCreate.getCoupons() != null) {
            Set<Coupon> coupons = orderCreate.getCoupons().stream()
                    .map(couponRepository::findByName)
                    .map(coupon -> coupon.orElseThrow(IllegalStateException::new))
                    .collect(Collectors.toSet());

            return OrderResponse.builder()
                    .amount(calculatorService.calculate(coupons, totalAmount))
                    .build();
        }

        return OrderResponse.builder().amount(totalAmount).build();
    }


}
