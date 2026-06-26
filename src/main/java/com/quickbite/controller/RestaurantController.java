package com.quickbite.controller;

import com.quickbite.dto.RestaurantRequest;
import com.quickbite.dto.RestaurantResponse;
import com.quickbite.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    @PostMapping
    public RestaurantResponse createRestaurant(@Valid  @RequestBody RestaurantRequest request){
        return restaurantService.createRestaurant(request);
    }
    @GetMapping
    public List<RestaurantResponse> getAllRestaurant(){
        return restaurantService.getAllRestaurant();
    }
    @GetMapping("/{id}")
    public RestaurantResponse getRestauranById(@PathVariable Long id){
        return restaurantService.getRestauranById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteRestaurant(@PathVariable  Long id){
        restaurantService.deleteRestaurant(id);
        return "Restaurant deleted successfully";
    }

    @PutMapping("/{id}")
    public RestaurantResponse updateRestaurant(@PathVariable  Long id,@Valid @RequestBody  RestaurantRequest request) {
        return restaurantService.updateRestaurant(id,request);
    }
}
