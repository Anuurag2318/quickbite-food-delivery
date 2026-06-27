package com.quickbite.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.quickbite.dto.OrderRequest;
import com.quickbite.dto.OrderResponse;
import com.quickbite.dto.OrderTrackingResponse;
import com.quickbite.dto.UpdateOrderStatusRequest;
import org.springframework.data.domain.Page;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest request) throws JsonProcessingException;
    Page<OrderResponse> getOrdersByUserId(Long userId,int page,int limit);
    OrderResponse updateOrderStatus(Long id, UpdateOrderStatusRequest request);
    OrderResponse cancelOrder(Long orderId);
    OrderTrackingResponse trackOrder(Long orderId);
}
