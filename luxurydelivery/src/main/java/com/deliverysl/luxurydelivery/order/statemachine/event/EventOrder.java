package com.deliverysl.luxurydelivery.order.statemachine.event;

public enum EventOrder {
    START_PENDING,
    CONFIRM,
    START_READY,
    START_DELIVERY,
    DELIVER,
    CANCEL
}
