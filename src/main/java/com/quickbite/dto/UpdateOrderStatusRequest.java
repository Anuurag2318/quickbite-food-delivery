package com.quickbite.dto;

import com.quickbite.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOrderStatusRequest {
    @NotNull(message="Status is required")
    private OrderStatus status;
}
