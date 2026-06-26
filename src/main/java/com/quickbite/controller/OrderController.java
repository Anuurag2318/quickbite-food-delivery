package com.quickbite.controller;

import com.quickbite.dto.OrderRequest;
import com.quickbite.dto.OrderResponse;
import com.quickbite.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderResponse placeOrder(@RequestBody OrderRequest request){
        return orderService.placeOrder(request);
    }
}
