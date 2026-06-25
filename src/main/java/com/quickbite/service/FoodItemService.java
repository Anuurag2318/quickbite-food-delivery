package com.quickbite.service;

import com.quickbite.dto.FoodItemRequest;
import com.quickbite.dto.FoodItemResponse;

public interface FoodItemService {
    FoodItemResponse createFoodItem(FoodItemRequest request);
}
