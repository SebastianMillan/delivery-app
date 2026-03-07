package com.deliverysl.luxurydelivery.order.dto;


import com.deliverysl.luxurydelivery.orderline.dto.CreateOrderlineDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record EditOrderDTO(
        List<CreateOrderlineDTO> orderlineDTOList
        //@NotBlank(message = "StateOrder cannot be empty")
        //String stateOrder
        //@NotNull(message = "IdEmployee cannot be null")
        //Long idEmployee,
        //@NotNull(message = "IdClient cannot be null")
        //Long idClient,
        //@NotNull(message = "IdRider cannot be null")
        //Long idRider
) {
}
