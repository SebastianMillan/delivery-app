package com.deliverysl.luxurydelivery.order.exception;

public class OrderNotOpenedException extends RuntimeException {
    public OrderNotOpenedException(String message) {
        super(message);
    }
    public OrderNotOpenedException(Long idOrder){
        super("Order with ID %d".formatted(idOrder)+" has not opened yet");
    }
}
