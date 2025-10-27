package com.deliverysl.luxurydelivery.orderline.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateOrderlineDTO(
        @Positive(message = "Quantity cannot be 0 or negative")
        int quantity,
        @NotNull(message = "idProduct cannot be null")
        Long idProduct
) {
}
