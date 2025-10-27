package com.deliverysl.luxurydelivery.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryCreateDTO(
        @NotBlank(message = "Name cannot be empty")
        String name,
        @NotBlank(message = "Description cannot be empty")
        String description,
        @NotNull(message = "IdRestaurant cannot be null")
        Long idRestaurant
) {
}
