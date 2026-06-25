package com.quickbite.service;

import com.quickbite.dto.FoodItemRequest;
import com.quickbite.dto.FoodItemResponse;

import java.util.List;

public interface FoodItemService {
    FoodItemResponse createFoodItem(FoodItemRequest request);
    List<FoodItemResponse> getAllFoodItems();
    FoodItemResponse getFoodItemById(Long id);
    FoodItemResponse updateFoodItem(Long id,FoodItemRequest request);
    void deleteFoodItem(Long id);
}
