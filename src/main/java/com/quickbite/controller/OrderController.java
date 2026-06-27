package com.quickbite.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.quickbite.dto.OrderRequest;
import com.quickbite.dto.OrderResponse;
import com.quickbite.dto.OrderTrackingResponse;
import com.quickbite.dto.UpdateOrderStatusRequest;
import com.quickbite.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderResponse placeOrder(@RequestBody OrderRequest request) throws JsonProcessingException {
        return orderService.placeOrder(request);
    }
    @GetMapping("/users/{userId}")
    public Page<OrderResponse> getOrdersByUserId(@PathVariable  Long userId,@RequestParam int page,@RequestParam int limit) {
        return orderService.getOrdersByUserId(userId,page,limit);
    }
    @PutMapping("/{orderId}/status")
    public OrderResponse updateOrderStatus(@PathVariable  Long orderId, @Valid @RequestBody UpdateOrderStatusRequest request) {
        return orderService.updateOrderStatus(orderId,request);
    }
    @PutMapping("/{orderId}/cancel")
    public OrderResponse cancelOrder(@PathVariable  Long orderId) {
        return orderService.cancelOrder(orderId);
    }
    @GetMapping("/{orderId}/track")
    public OrderTrackingResponse trackOrder(@PathVariable Long orderId){
        return orderService. trackOrder(orderId);
    }
}
