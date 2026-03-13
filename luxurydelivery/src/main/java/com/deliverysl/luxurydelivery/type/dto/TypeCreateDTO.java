package com.deliverysl.luxurydelivery.type.dto;

import jakarta.validation.constraints.NotBlank;

public record TypeCreateDTO(
        @NotBlank(message = "Name cannot be empty")
        String name,
        @NotBlank(message = "Description cannot be empty")
        String description
) {
}
