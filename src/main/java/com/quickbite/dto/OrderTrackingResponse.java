package com.quickbite.dto;

import com.quickbite.entity.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderTrackingResponse {
    private Long orderId;
    private OrderStatus status;
    private String estimatedDeliveryTime;
}
