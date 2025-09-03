package com.deliverysl.luxurydelivery.order.dto;

import com.deliverysl.luxurydelivery.orderline.dto.CreateOrderlineDTO;

import java.util.List;

public record CreateOrderDTO(
        List<CreateOrderlineDTO> orderlineDTOList,
        Long idEmployee,
        Long idClient,
        Long idRider
) {
}
