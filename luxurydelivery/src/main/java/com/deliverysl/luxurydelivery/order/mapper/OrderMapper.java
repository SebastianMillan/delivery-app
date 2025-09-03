package com.deliverysl.luxurydelivery.order.mapper;

import com.deliverysl.luxurydelivery.order.dto.CreateOrderDTO;
import com.deliverysl.luxurydelivery.order.dto.EditOrderDto;
import com.deliverysl.luxurydelivery.order.dto.OrderDTO;
import com.deliverysl.luxurydelivery.order.model.Order;
import com.deliverysl.luxurydelivery.order.model.StateOrder;
import com.deliverysl.luxurydelivery.orderline.mapper.OrderlineMapper;
import com.deliverysl.luxurydelivery.user.model.Client;
import com.deliverysl.luxurydelivery.user.model.Employee;
import com.deliverysl.luxurydelivery.user.model.Rider;
import lombok.RequiredArgsConstructor;
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
                order.getCreateDate(),
                order.getOrderlineList().stream()
                        .map(orderlineMapper::toDto)
                        .toList(),
                order.getTotal(),
                order.getEmployee().getName(),
                order.getClient().getName(),
                order.getRider().getName(),
                order.isActivate()
        );

    }

    public Order toEntity(OrderDTO orderDTO, Employee employee, Client client, Rider rider){

        return Order.builder()
                .id(orderDTO.id())
                .createDate(orderDTO.dateTime())
                .total(orderDTO.total())
                .orderlineList(new ArrayList<>())
                .activate(orderDTO.activate())
                .employee(employee)
                .client(client)
                .rider(rider)
                .build();

    }

    public CreateOrderDTO createOrderDTO(Order order){
        return new CreateOrderDTO(
                order.getOrderlineList().stream()
                        .map(orderlineMapper::toCreateDto)
                        .toList(),
                order.getEmployee().getId(),
                order.getClient().getId(),
                order.getRider().getId()
        );
    }

    public Order toEntity(CreateOrderDTO createOrderDTO,Employee employee,Client client,Rider rider){
        return Order.builder()
                .orderlineList(new ArrayList<>())
                .employee(employee)
                .client(client)
                .rider(rider)
                //.active(createOrderDTO.active())
                .build();
    }

    public EditOrderDto editOrderDto(Order order){
        return new EditOrderDto(
                String.valueOf(order.getStateOrder()),
                order.getEmployee().getId(),
                order.getClient().getId(),
                order.getRider().getId()
        );
    }

    public Order toEntity(EditOrderDto editOrderDto,Employee employee,Client client,Rider rider){
        return Order.builder()
                .stateOrder(StateOrder.valueOf(editOrderDto.stateOrder()))
                .employee(employee)
                .client(client)
                .rider(rider)
                .build();
    }

}
