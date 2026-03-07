package com.deliverysl.luxurydelivery.order.exception;

public class OrderWithoutOrderlinesException extends RuntimeException {
    public OrderWithoutOrderlinesException(String message) {
        super(message);
    }
    public OrderWithoutOrderlinesException(Long idOrder) {
        super("Order with ID %d".formatted(idOrder)+" has no active Orderlines");
    }
}
