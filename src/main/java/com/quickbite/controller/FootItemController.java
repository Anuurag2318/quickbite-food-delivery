package com.quickbite.controller;

import com.quickbite.dto.FoodItemRequest;
import com.quickbite.dto.FoodItemResponse;
import com.quickbite.service.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food-items")
@RequiredArgsConstructor
public class FootItemController {
    private final FoodItemService foodItemService;
    @PostMapping
    public FoodItemResponse createFoodItem(@RequestBody  FoodItemRequest request) {
        return foodItemService.createFoodItem(request);
    }
    @GetMapping
    public List<FoodItemResponse> getAllFoodItems(){
        return foodItemService.getAllFoodItems();
    }
    @GetMapping("/{id}")
    public FoodItemResponse getFoodItemById(@PathVariable  Long id){
        return foodItemService.getFoodItemById(id);
    }
    @PutMapping("/{id}")
    FoodItemResponse updateFoodItem(@PathVariable  Long id,@RequestBody FoodItemRequest request){
        return foodItemService.updateFoodItem(id,request);
    }
    @DeleteMapping("/{id}")
    public String deleteFoodItem(@PathVariable  Long id){
        foodItemService.deleteFoodItem(id);
        return "Food item deleted Successfully";
    }
}
