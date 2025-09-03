package com.deliverysl.luxurydelivery.order.dto;


public record EditOrderDto(
        String stateOrder,
        Long idEmployee,
        Long idClient,
        Long idRider
) {
}
