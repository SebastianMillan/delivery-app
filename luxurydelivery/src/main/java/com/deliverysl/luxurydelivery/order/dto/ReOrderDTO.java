package com.deliverysl.luxurydelivery.order.dto;

import com.deliverysl.luxurydelivery.orderline.dto.CreateOrderlineDTO;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ReOrderDTO(
        @NotNull(message = "IdClient cannot be null")
        Long idClient,
        List<CreateOrderlineDTO> orderlineDTOList
) {
}
