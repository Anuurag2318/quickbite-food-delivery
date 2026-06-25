package com.quickbite.controller;

import com.quickbite.dto.FoodItemRequest;
import com.quickbite.dto.FoodItemResponse;
import com.quickbite.service.FoodItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/food-items")
@RequiredArgsConstructor
public class FootItemController {
    private final FoodItemService foodItemService;
    @PostMapping
    public FoodItemResponse createFoodItem(@RequestBody  FoodItemRequest request) {
        return foodItemService.createFoodItem(request);
    }
}
