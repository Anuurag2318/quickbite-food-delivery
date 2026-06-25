package com.quickbite.service;

import com.quickbite.dto.FoodItemRequest;
import com.quickbite.dto.FoodItemResponse;
import com.quickbite.entity.FoodItem;
import com.quickbite.entity.Restaurant;
import com.quickbite.exception.ResourceNotFoundException;
import com.quickbite.repository.FoodItemRepository;
import com.quickbite.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodItemServiceImpl implements FoodItemService{
    private final FoodItemRepository foodItemRepository;
    private final RestaurantRepository restaurantRepository;
    @Override
    public FoodItemResponse createFoodItem(FoodItemRequest request) {
        Restaurant restaurant=restaurantRepository.findById(request.getRestaurantId()).orElseThrow(()->new ResourceNotFoundException("Restaurant not found"));
        FoodItem foodItem=FoodItem.
        builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .restaurant(restaurant).build();
        FoodItem saved=foodItemRepository.save(foodItem);
        return FoodItemResponse.builder()
                .id(saved.getId())
                .description(saved.getDescription())
                .restaurantName(restaurant.getName())
                .price(saved.getPrice())
                .name(saved.getName()).build();
    }
}
