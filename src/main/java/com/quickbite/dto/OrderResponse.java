package com.quickbite.dto;

import com.quickbite.entity.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {
    private Long id;
    private Double totalAmount;
    private OrderStatus status;
}
