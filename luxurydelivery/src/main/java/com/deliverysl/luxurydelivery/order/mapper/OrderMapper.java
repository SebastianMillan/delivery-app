package com.deliverysl.luxurydelivery.order.mapper;

import com.deliverysl.luxurydelivery.order.dto.CreateOrderDTO;
import com.deliverysl.luxurydelivery.order.dto.EditOrderDto;
import com.deliverysl.luxurydelivery.order.dto.OrderDTO;
import com.deliverysl.luxurydelivery.order.model.Order;
import com.deliverysl.luxurydelivery.order.model.StateOrder;
import com.deliverysl.luxurydelivery.orderline.mapper.OrderlineMapper;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final OrderlineMapper orderlineMapper;

    public OrderDTO toDto(Order order){
        return new OrderDTO(
                order.getId(),
                order.getStateOrder().name(),
                order.getDateTime(),
                order.getOrderlineList().stream()
                        .map(orderlineMapper::toShortDto)
                        .toList(),
                order.getTotal()
        );

    }

    public Order toEntity(OrderDTO orderDTO){
        return Order.builder()
                .id(orderDTO.id())
                .dateTime(orderDTO.dateTime())
                .total(orderDTO.total())
                .orderlineList(new ArrayList<>()) // Se crean en el servicio
                //.employee()
                //.client()
                //.rider()
                .build();

    }

    public CreateOrderDTO createOrderDTO(Order order){
        return new CreateOrderDTO(
                String.valueOf(order.getStateOrder()),
                order.getOrderlineList().stream()
                        .map(orderlineMapper::toCreateDto)
                        .toList()
        );
    }

    public Order toEntity(CreateOrderDTO createOrderDTO){
        return Order.builder()
                .stateOrder(StateOrder.valueOf(createOrderDTO.stateOrder()))
                .orderlineList(new ArrayList<>())
                .build();
    }

    public EditOrderDto editOrderDto(Order order){
        return new EditOrderDto(
                String.valueOf(order.getStateOrder())
        );
    }

    public Order toEntity(EditOrderDto editOrderDto){
        return Order.builder()
                .stateOrder(StateOrder.valueOf(editOrderDto.stateOrder()))
                .build();
    }

}
