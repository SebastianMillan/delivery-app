package com.deliverysl.luxurydelivery.orderline.dto;

import java.math.BigDecimal;

public record CreateOrderlineDTO(
        int quantity,
        Long idProduct
) {
}
