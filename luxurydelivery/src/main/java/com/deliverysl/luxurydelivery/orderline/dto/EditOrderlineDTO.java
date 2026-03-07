package com.deliverysl.luxurydelivery.orderline.dto;

import jakarta.validation.constraints.Positive;

public record EditOrderlineDTO(
        @Positive(message = "Quantity cannot be 0 or negative")
        int quantity
) {
}
