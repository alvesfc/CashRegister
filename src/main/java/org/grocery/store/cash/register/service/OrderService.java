package org.grocery.store.cash.register.service;

import org.grocery.store.cash.register.rest.resource.OrderCreate;
import org.grocery.store.cash.register.rest.resource.OrderResponse;

public interface OrderService {

    OrderResponse proccess(final OrderCreate orderCreate);
}
