package com.deliverysl.luxurydelivery.order.exception;

public class OrderCannotBeReorder extends RuntimeException {
    public OrderCannotBeReorder(String message) {
        super(message);
    }
    public OrderCannotBeReorder(Long idOrder) {
        super("Order with ID %d".formatted(idOrder)+" cannot be reorder");
    }
}
