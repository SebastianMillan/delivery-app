package com.deliverysl.luxurydelivery.order.statemachine.state;

public enum  StateOrder {
    OPENED,//Estado para que el usuario pueda modificar el pedido antes de realizar el pedido
    PENDING,
    IN_KITCHEN,
    READY,
    ON_DELIVERY,
    DELIVERED,
    CANCELLED
}
