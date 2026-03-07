package com.deliverysl.luxurydelivery.order.exception;

import com.deliverysl.luxurydelivery.order.statemachine.event.EventOrder;
import com.deliverysl.luxurydelivery.order.statemachine.state.StateOrder;
import org.springframework.statemachine.StateMachine;

public class OrderInvalidStateTransitionException extends RuntimeException {
    public OrderInvalidStateTransitionException(String message) {
        super(message);
    }
    public OrderInvalidStateTransitionException(Long idOrder, StateOrder stateOrder, EventOrder eventOrder){
        super("Order with ID %d".formatted(idOrder)+" has not transitioned " + stateOrder + " using " + eventOrder);
    }
}
