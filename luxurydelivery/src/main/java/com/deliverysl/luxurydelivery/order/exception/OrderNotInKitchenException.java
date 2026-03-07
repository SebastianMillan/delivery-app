package com.deliverysl.luxurydelivery.order.exception;

public class OrderNotInKitchenException extends RuntimeException {
    public OrderNotInKitchenException(String message) {
        super(message);
    }
    public OrderNotInKitchenException(Long idOrder) {
        super("Order with ID %d".formatted(idOrder)+" has not in kitchen");
    }
}
