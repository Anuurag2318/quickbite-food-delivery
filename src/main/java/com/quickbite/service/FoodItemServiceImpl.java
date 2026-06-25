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

    @Override
    public List<FoodItemResponse> getAllFoodItems() {
        return foodItemRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public FoodItemResponse getFoodItemById(Long id) {
        FoodItem foodItem=foodItemRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Food Item not found"));
        return mapToResponse(foodItem);
    }

    @Override
    public FoodItemResponse updateFoodItem(Long id, FoodItemRequest request) {
        Restaurant restaurant=restaurantRepository.findById(request.getRestaurantId()).orElseThrow(()->new ResourceNotFoundException("Restaurant not found"));
        FoodItem foodItem=foodItemRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Food Item not found"));
        foodItem.setName(request.getName());
        foodItem.setDescription(request.getDescription());
        foodItem.setPrice(request.getPrice());
        foodItem.setRestaurant(restaurant);
        FoodItem updated=foodItemRepository.save(foodItem);
        return mapToResponse(updated);
    }

    @Override
    public void deleteFoodItem(Long id) {
        foodItemRepository.deleteById(id);
    }
    private FoodItemResponse mapToResponse(FoodItem foodItem){
        return FoodItemResponse.builder()
                .id(foodItem.getId())
                .description(foodItem.getDescription())
                .restaurantName(foodItem.getRestaurant().getName())
                .price(foodItem.getPrice())
                .name(foodItem.getName()).build();
    }
}
