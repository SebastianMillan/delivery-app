package com.deliverysl.luxurydelivery.restaurant.dto;

public record RestaurantDTO(
        Long id,
        String name,
        String avatar,
        double rating,
        String type
        //int employeeNumber
) {
}
