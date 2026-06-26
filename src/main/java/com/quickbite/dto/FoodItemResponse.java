package com.quickbite.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class FoodItemResponse implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String restaurantName;
}
