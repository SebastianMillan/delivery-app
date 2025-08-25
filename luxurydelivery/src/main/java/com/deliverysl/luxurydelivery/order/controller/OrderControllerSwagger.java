package com.deliverysl.luxurydelivery.order.controller;

import com.deliverysl.luxurydelivery.order.dto.CreateOrderDTO;
import com.deliverysl.luxurydelivery.order.dto.EditOrderDto;
import com.deliverysl.luxurydelivery.order.dto.OrderDTO;
import com.deliverysl.luxurydelivery.orderline.dto.CreateOrderlineDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderControllerSwagger {
    ResponseEntity<List<OrderDTO>> findAll();
    ResponseEntity<OrderDTO> findById(Long id);
    ResponseEntity<OrderDTO> create(CreateOrderDTO createOrderDTO);
    ResponseEntity<OrderDTO> edit(EditOrderDto editOrderDTO, Long id);
    ResponseEntity<?> delete(Long id);
}
