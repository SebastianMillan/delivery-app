package com.deliverysl.luxurydelivery.order.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditOrderDto(
        @NotBlank(message = "StateOrder cannot be empty")
        String stateOrder,
        @NotNull(message = "IdEmployee cannot be null")
        Long idEmployee,
        @NotNull(message = "IdClient cannot be null")
        Long idClient,
        @NotNull(message = "IdRider cannot be null")
        Long idRider
) {
}
