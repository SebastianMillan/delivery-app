package com.deliverysl.luxurydelivery.restaurant.controller;

import com.deliverysl.luxurydelivery.restaurant.dto.RestaurantDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface RestaurantControllerSwagger {

    ResponseEntity<List<RestaurantDTO>>findAll();
    ResponseEntity<RestaurantDTO> findById(Long id);
    ResponseEntity<RestaurantDTO> create(RestaurantDTO restaurantDTO);
    ResponseEntity<RestaurantDTO>edit(RestaurantDTO restaurantDTO, Long id);
    ResponseEntity<?>delete(Long id);
}
