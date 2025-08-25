package com.deliverysl.luxurydelivery.orderline.dto;

import java.math.BigDecimal;

public record ShortOrderlineDTO(
        Long id,
        String nameProduct,
        int quantity,
        BigDecimal subtotal
) {

}
