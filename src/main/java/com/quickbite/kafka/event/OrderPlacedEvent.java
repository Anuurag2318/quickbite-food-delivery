package com.quickbite.kafka.event;

import com.quickbite.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPlacedEvent {
    private Long orderId;
    private Long userId;
    private Double totalAmount;
    private OrderStatus status;
}
