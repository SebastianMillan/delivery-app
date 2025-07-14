package com.deliverysl.luxurydelivery.category.dto;

public record CategoryCreateDTO(
        String name,
        String description,
        Long idRestaurant
) {
}
