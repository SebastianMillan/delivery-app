package com.deliverysl.luxurydelivery.orderline.dto;

public record CreateOrderlineDTO(
        int quantity,
        Long idProduct
) {
}
