package com.deliverysl.luxurydelivery.order.controller;

import com.deliverysl.luxurydelivery.allergen.dto.CreateAllergenDTO;
import com.deliverysl.luxurydelivery.order.dto.CreateOrderDTO;
import com.deliverysl.luxurydelivery.order.dto.EditOrderDto;
import com.deliverysl.luxurydelivery.order.dto.OrderDTO;
import com.deliverysl.luxurydelivery.order.mapper.OrderMapper;
import com.deliverysl.luxurydelivery.order.model.Order;
import com.deliverysl.luxurydelivery.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController implements OrderControllerSwagger{

    private final OrderService orderService;
    private final OrderMapper orderMapper;


    @GetMapping
    @Override
    public ResponseEntity<List<OrderDTO>> findAll() {

        List<Order>orderList = orderService.findAll();

        return orderList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(orderList.stream().map(orderMapper::toDto).toList());

    }

    @GetMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(orderMapper.toDto(orderService.findByIdOrThrow(id)));
    }

    @PostMapping
    @Override
    public ResponseEntity<OrderDTO> create(@RequestBody CreateOrderDTO createOrderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderMapper.toDto(orderService.create(createOrderDTO)));
    }

    @PutMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<OrderDTO> edit(@RequestBody EditOrderDto editOrderDTO, @PathVariable Long id) {
        return ResponseEntity.ok(orderMapper.toDto(orderService.edit(id,editOrderDTO)));
    }

    @DeleteMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<?> delete(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id:[0-9]+}/activate")
    @Override
    public ResponseEntity<OrderDTO> activate(@PathVariable Long id) {
        return ResponseEntity.ok(orderMapper.toDto(orderService.activate(id)));
    }

    @Override
    @GetMapping("/enable")
    public ResponseEntity<List<OrderDTO>> findByActivateTrue() {
        List<Order> orderList = orderService.findByActivateTrue();

        return orderList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(orderList.stream()
                        .map(orderMapper::toDto)
                        .toList());
    }

    @Override
    @GetMapping("/disable")
    public ResponseEntity<List<OrderDTO>> findByActivateFalse() {

        List<Order> orderList = orderService.findByActivateFalse();

        return orderList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(orderList.stream()
                        .map(orderMapper::toDto)
                        .toList());
    }

}
