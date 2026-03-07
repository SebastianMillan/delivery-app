package com.deliverysl.luxurydelivery.order.controller;

import com.deliverysl.luxurydelivery.order.dto.CreateOrderDTO;
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
@RequestMapping("/orders")
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

    /*@DeleteMapping("/{id}")
    @Override
    public ResponseEntity<?> deactive(@PathVariable Long id){
        orderService.deactivate(id);
        return ResponseEntity.noContent().build();
    }*/

    /*@DeleteMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<?> deactive(@PathVariable Long id) {
        orderService.deactive(id);
        return ResponseEntity.noContent().build();
    }*/

    /*@PatchMapping("/{id:[0-9]+}/activate")
    @Override
    public ResponseEntity<OrderDTO> toggle(@PathVariable Long id) {
        return ResponseEntity.ok(orderMapper.toDto(orderService.deactivate(id)));
    }*/

    @Override
    @GetMapping("/enable")
    public ResponseEntity<List<OrderDTO>> findAllByActivateTrue() {
        List<Order> orderList = orderService.findAllByActiveTrue();

        return orderList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(orderList.stream()
                        .map(orderMapper::toDto)
                        .toList());
    }

    @Override
    @PutMapping("/{id:[0-9]+}/confirmOrder")
    public ResponseEntity<OrderDTO> clientConfirmOrder(@PathVariable Long id){
        return ResponseEntity.ok(orderMapper.toDto(orderService.clientConfirmOrder(id)));
    }

    @Override
    @PutMapping("/{id:[0-9]+}/cancelOrder")
    public ResponseEntity<OrderDTO>clientCancelOrder(@PathVariable Long id){
        return ResponseEntity.ok(orderMapper.toDto(orderService.clientCancelOrder(id)));
    }

    @Override
    @PostMapping("/{id:[0-9]+}/reOrder")
    public ResponseEntity<OrderDTO>clientReOrder(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderMapper.toDto(orderService.clientReorder(id)));
    }

    @Override
    @PutMapping("/{id:[0-9]+}/bossConfirmOrder")
    public ResponseEntity<OrderDTO> bossConfirmOrder(@PathVariable Long id,@RequestBody Long idEmployee){
        return ResponseEntity.ok(orderMapper.toDto(orderService.bossConfirmOrder(id,idEmployee)));
    }

    @Override
    @PutMapping("/{id:[0-9]+}/employeeStartReady")
    public ResponseEntity<OrderDTO> employeeStartReady(@PathVariable Long id){
        return ResponseEntity.ok(orderMapper.toDto(orderService.employeeStartReady(id)));
    }

    @Override
    @PutMapping("/{id:[0-9]+}/riderStartDelivery")
    public ResponseEntity<OrderDTO> riderStartDelivery(@PathVariable Long id, @RequestBody Long idRider){
        return ResponseEntity.ok(orderMapper.toDto(orderService.riderStartDelivery(id,idRider)));
    }

    @Override
    @PutMapping("/{id:[0-9]+}/riderDelivered")
    public ResponseEntity<OrderDTO> riderDelivered(@PathVariable Long id, @RequestBody Long idRider){
        return ResponseEntity.ok(orderMapper.toDto(orderService.riderDelivered(id,idRider)));
    }

}
