package com.deliverysl.luxurydelivery.order.dto;

import com.deliverysl.luxurydelivery.orderline.dto.CreateOrderlineDTO;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderDTO(
        //List<CreateOrderlineDTO> orderlineDTOList,
        //Al crear el pedido se pedirá el id del cliente.Entiendo que el flujo del cliente sería crear pedido, y posteriormente añadir las lineas de pedido.
        @NotNull(message = "IdClient cannot be null")
        Long idClient
        /*@NotNull(message = "IdEmployee cannot be null")
        Long idEmployee
        @NotNull(message = "IdRider cannot be null")
        Long idRider*/
) {
}
