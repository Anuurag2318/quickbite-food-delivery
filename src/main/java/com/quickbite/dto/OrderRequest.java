package com.quickbite.dto;

import com.quickbite.entity.FoodItem;
import com.quickbite.entity.OrderItem;
import com.quickbite.entity.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderRequest {
    private Long userId;
    private List<OrderItemRequest> items;
}
