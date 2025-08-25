package com.deliverysl.luxurydelivery.order.exception;

import jakarta.persistence.EntityNotFoundException;

public class OrderNotFoundException extends EntityNotFoundException {

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(){
        super("Orders not found");
    }

    public OrderNotFoundException(Long id){
        super("Order not found id %d".formatted(id));
    }

}
