package com.deliverysl.luxurydelivery.order.dto;

import com.deliverysl.luxurydelivery.orderline.dto.OrderlineDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        Long id,
        String stateOrder,
        LocalDateTime dateTime,
        List<OrderlineDTO> orderlineDTOList,
        BigDecimal total,
        boolean activate
        //String nameEmploye
        //String nameRider
        //String nameClient
) {
}
