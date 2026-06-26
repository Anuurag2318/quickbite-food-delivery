package com.quickbite.dto;

import com.quickbite.entity.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponse implements Serializable {
    private Long id;
    private String name;
    private String address;
    private String cuisineType;
    private Double rating;
    private List<FoodItem> foodItems;
}
