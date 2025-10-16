package com.deliverysl.luxurydelivery.orderline.exception;

import jakarta.persistence.EntityNotFoundException;

public class OrderlineNotFoundException extends EntityNotFoundException {

    public OrderlineNotFoundException(String message) {
        super(message);
    }

    public OrderlineNotFoundException(){
        super("Orderlines not found");
    }

    public OrderlineNotFoundException(Long id){
        super("Orderline not found id %d".formatted(id));
    }

}
