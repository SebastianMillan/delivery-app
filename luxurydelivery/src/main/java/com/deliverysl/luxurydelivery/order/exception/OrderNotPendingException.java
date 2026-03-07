package com.deliverysl.luxurydelivery.order.exception;

public class OrderNotPendingException extends RuntimeException {
    public OrderNotPendingException(String message) {
        super(message);
    }
    public OrderNotPendingException(Long idOrder) {
        super("Order with ID %d".formatted(idOrder)+" has not pending");
    }
}
