package com.quickbite.service;

import com.quickbite.dto.RestaurantRequest;
import com.quickbite.dto.RestaurantResponse;
import com.quickbite.entity.Restaurant;
import com.quickbite.exception.ResourceNotFoundException;
import com.quickbite.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService{
    private final RestaurantRepository restaurantRepository;
    @Override
    @CacheEvict(value="restaurants",allEntries = true)
    public RestaurantResponse createRestaurant(RestaurantRequest request) {
        Restaurant restaurant=Restaurant.builder().name(request.getName())
                .address(request.getAddress())
                .cuisineType(request.getCuisineType())
                .rating(request.getRating())
                .build();
        Restaurant saved=restaurantRepository.save(restaurant);
        return mapToResponse(saved);
    }
    @Override
    @Cacheable(value="restaurants")
    public List<RestaurantResponse> getAllRestaurant() {
        return restaurantRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Cacheable(value="restaurants",key="#id")
    public RestaurantResponse getRestauranById(Long id) {
        System.out.println("Fetching from DB");
        Restaurant restaurant=restaurantRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Restaurant not found"));
        return mapToResponse(restaurant);
    }

    @Override
    @CacheEvict(value="restaurants",allEntries = true)
    public void deleteRestaurant(Long id) {
        Restaurant restaurant=restaurantRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Restaurant not present"));
        restaurantRepository.delete(restaurant);
    }

    @Override
    @CacheEvict(value="restaurants",allEntries = true)
    public RestaurantResponse updateRestaurant(Long id, RestaurantRequest request) {
        Restaurant restaurant=restaurantRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Restaurant not present"));
        restaurant.setName(request.getName());
        restaurant.setAddress(request.getAddress());
        restaurant.setRating(request.getRating());
        restaurant.setCuisineType(request.getCuisineType());
        Restaurant updated=restaurantRepository.save(restaurant);
        return mapToResponse(updated);
    }
    private RestaurantResponse mapToResponse(Restaurant restaurant) {
        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .rating(restaurant.getRating())
                .cuisineType(restaurant.getCuisineType())
                .build();
    }
}
