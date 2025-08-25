package com.deliverysl.luxurydelivery.orderline.controller;

import com.deliverysl.luxurydelivery.order.mapper.OrderMapper;
import com.deliverysl.luxurydelivery.order.model.Order;
import com.deliverysl.luxurydelivery.order.service.OrderService;
import com.deliverysl.luxurydelivery.orderline.dto.CreateOrderlineDTO;
import com.deliverysl.luxurydelivery.orderline.dto.OrderlineDTO;
import com.deliverysl.luxurydelivery.orderline.exception.OrderlineNotFoundException;
import com.deliverysl.luxurydelivery.orderline.mapper.OrderlineMapper;
import com.deliverysl.luxurydelivery.orderline.model.Orderline;
import com.deliverysl.luxurydelivery.orderline.service.OrderlineService;
import com.deliverysl.luxurydelivery.product.model.Product;
import com.deliverysl.luxurydelivery.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders/{orderId}/orderline")
public class OrderlineController implements OrderlineControllerSwagger {

    private final OrderService orderService;
    private final OrderlineMapper orderlineMapper;

    @GetMapping
    @Override
    public ResponseEntity<List<OrderlineDTO>> findAll(@PathVariable Long orderId) {

        Order order = orderService.findByIdOrThrow(orderId);

        List<Orderline> orderlineList = order.getOrderlineList();

        return orderlineList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(orderlineList.stream().map(orderlineMapper::toDto).toList());

    }

    @GetMapping("/{orderlineId}")
    @Override
    public ResponseEntity<OrderlineDTO> findById(@PathVariable Long orderId,@PathVariable Long orderlineId) {

        Orderline orderline = orderService.getOneOrderline(orderId,orderlineId);
        return ResponseEntity.ok(orderlineMapper.toDto(orderline));
    }

    @PostMapping
    @Override
    public ResponseEntity<OrderlineDTO> create(@PathVariable Long orderId,@RequestBody CreateOrderlineDTO createOrderlineDTO) {

        Orderline orderline = orderService.addOrderLine(orderId,createOrderlineDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderlineMapper.toDto(orderline));
    }

    @PutMapping("/{orderlineId}")
    @Override
    public ResponseEntity<OrderlineDTO> edit(@PathVariable Long orderId,@PathVariable Long orderlineId,@RequestBody CreateOrderlineDTO createOrderlineDTO) {

        Orderline orderline = orderService.editOrdeline(orderId,orderlineId,createOrderlineDTO);

        return ResponseEntity.ok(orderlineMapper.toDto(orderline));
    }

    @DeleteMapping("/{orderlineId}")
    @Override
    public ResponseEntity<?> delete(@PathVariable Long orderId,@PathVariable Long orderlineId) {
        orderService.deleteOrderline(orderId,orderlineId);
        return ResponseEntity.noContent().build();
    }
}
