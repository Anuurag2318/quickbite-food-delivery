package com.quickbite.service;

import com.quickbite.dto.OrderRequest;
import com.quickbite.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest request);
}
