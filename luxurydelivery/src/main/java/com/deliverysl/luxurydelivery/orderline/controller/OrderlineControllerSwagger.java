package com.deliverysl.luxurydelivery.orderline.controller;

import com.deliverysl.luxurydelivery.orderline.dto.CreateOrderlineDTO;
import com.deliverysl.luxurydelivery.orderline.dto.OrderlineDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderlineControllerSwagger {

    ResponseEntity<List<OrderlineDTO>> findAll(Long orderId);
    ResponseEntity<OrderlineDTO> findById(Long orderId,Long orderlineId);
    ResponseEntity<OrderlineDTO> create(Long orderId,CreateOrderlineDTO createOrderlineDTO);
    ResponseEntity<OrderlineDTO> edit(Long orderId,Long orderlineId,CreateOrderlineDTO createOrderlineDTO);
    ResponseEntity<?> delete(Long orderId,Long orderlineId);

}
