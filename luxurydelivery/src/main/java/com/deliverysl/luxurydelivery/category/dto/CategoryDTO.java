package com.deliverysl.luxurydelivery.category.dto;


public record CategoryDTO(
        Long id,
        String name,
        String description,
        boolean activate
) {
}
