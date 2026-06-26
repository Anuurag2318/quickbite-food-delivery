package com.quickbite.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FoodItemRequest {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Description is required")
    private String description;
    @NotBlank(message = "Price is required")
    private Double price;
    @NotBlank(message = "RestaurantId is required")
    private Long restaurantId;
}
