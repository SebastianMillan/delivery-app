package com.deliverysl.luxurydelivery.order.mapper;

import com.deliverysl.luxurydelivery.order.dto.CreateOrderDTO;
import com.deliverysl.luxurydelivery.order.dto.EditOrderDTO;
import com.deliverysl.luxurydelivery.order.dto.OrderDTO;
import com.deliverysl.luxurydelivery.order.model.Order;
import com.deliverysl.luxurydelivery.orderline.mapper.OrderlineMapper;
import com.deliverysl.luxurydelivery.user.model.Client;
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
                //Evitamos el NPE
                order.getEmployee() != null ? order.getEmployee().getName() : null,
                order.getClient().getName(),
                //Evitamos el NPE
                order.getRider() != null ? order.getRider().getName() : null,
                order.isActive()
        );

    }

    public Order toEntity(OrderDTO orderDTO,Client client){

        return Order.builder()
                .id(orderDTO.id())
                .createDate(orderDTO.dateTime())
                .total(orderDTO.total())
                .orderlineList(new ArrayList<>())
                .client(client)
                .build();

    }

    public CreateOrderDTO createOrderDTO(Order order){
        return new CreateOrderDTO(
                order.getClient().getId()
        );
    }

    public Order toEntity(CreateOrderDTO createOrderDTO,Client client){
        return Order.builder()
                .orderlineList(new ArrayList<>())
                .client(client)
                .build();
    }

    public EditOrderDTO editOrderDto(Order order){
        return new EditOrderDTO(
                order.getOrderlineList().stream()
                        .map(orderlineMapper::toCreateDto)
                        .toList());
    }

}
