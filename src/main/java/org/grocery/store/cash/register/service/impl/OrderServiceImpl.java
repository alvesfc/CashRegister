package org.grocery.store.cash.register.service.impl;

import org.grocery.store.cash.register.rest.resource.OrderCreate;
import org.grocery.store.cash.register.rest.resource.OrderResponse;
import org.grocery.store.cash.register.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public OrderResponse proccess(OrderCreate orderCreate) {
        return null;
    }

}
