package com.deliverysl.luxurydelivery.orderline.dto;

import java.math.BigDecimal;

public record OrderlineDTO(
        Long id,
        String product,
        int quantity,
        BigDecimal subtotal
) {
}
