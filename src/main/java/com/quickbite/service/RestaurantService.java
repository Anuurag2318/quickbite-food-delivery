package com.quickbite.service;

import com.quickbite.dto.RestaurantRequest;
import com.quickbite.dto.RestaurantResponse;

import java.util.List;

public interface RestaurantService{
    RestaurantResponse createRestaurant(RestaurantRequest request);
    List<RestaurantResponse> getAllRestaurant();
    RestaurantResponse getRestauranById(Long id);
    void deleteRestaurant(Long id);
    RestaurantResponse updateRestaurant(Long id,RestaurantRequest request);
}
