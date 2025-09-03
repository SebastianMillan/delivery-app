package com.deliverysl.luxurydelivery.orderline.mapper;

import com.deliverysl.luxurydelivery.orderline.dto.CreateOrderlineDTO;
import com.deliverysl.luxurydelivery.orderline.dto.OrderlineDTO;
import com.deliverysl.luxurydelivery.orderline.model.Orderline;
import com.deliverysl.luxurydelivery.product.model.Product;
import org.springframework.stereotype.Component;

@Component
public class OrderlineMapper {

    public OrderlineDTO toDto(Orderline orderline){
        return new OrderlineDTO(
                orderline.getId(),
                orderline.getProduct().getName(),
                orderline.getQuantity(),
                orderline.getSubtotal(),
                orderline.isActivate()
        );
    }

    public Orderline toEntity(OrderlineDTO orderlineDTO,Product product){
        return Orderline.builder()
                .id(orderlineDTO.id())
                .quantity(orderlineDTO.quantity())
                .subtotal(orderlineDTO.subtotal())
                .product(product)
                .activate(orderlineDTO.activate())
                .build();
    }

    public CreateOrderlineDTO toCreateDto(Orderline orderline){
        return new CreateOrderlineDTO(
                orderline.getQuantity(),
                orderline.getProduct().getId()
        );
    }

    public Orderline toEntity(CreateOrderlineDTO createOrderlineDTO,Product product){
        return Orderline.builder()
                .quantity(createOrderlineDTO.quantity())
                .product(product)
                .build();
    }

}
