package com.deliverysl.luxurydelivery.order.statemachine;

import com.deliverysl.luxurydelivery.order.statemachine.event.EventOrder;
import com.deliverysl.luxurydelivery.order.statemachine.state.StateOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
// Nos permite obtener una máquina de estados por pedido
@EnableStateMachineFactory
@RequiredArgsConstructor
public class OrderStateMachineConfig
        extends EnumStateMachineConfigurerAdapter<StateOrder, EventOrder> {

    //Configuración de los estados
    @Override
    public void configure(StateMachineStateConfigurer<StateOrder, EventOrder> statesConfigurer) throws Exception {
       statesConfigurer.withStates()
               .initial(StateOrder.OPENED)
               .end(StateOrder.DELIVERED) // Posible estado final 1
               .end(StateOrder.CANCELLED) // Posible estado final 2
               .states(EnumSet.allOf(StateOrder.class));
    }

    //Transiciones entre estados
    @Override
    public void configure(StateMachineTransitionConfigurer<StateOrder, EventOrder> transitions) throws Exception {

        transitions
                //Flujo normal
                .withExternal()
                .source(StateOrder.OPENED).target(StateOrder.PENDING)
                .event(EventOrder.START_PENDING)
                .and()
                .withExternal()
                .source(StateOrder.PENDING).target(StateOrder.IN_KITCHEN)
                .event(EventOrder.CONFIRM)
                .and()
                .withExternal()
                .source(StateOrder.IN_KITCHEN).target(StateOrder.READY)
                .event(EventOrder.START_READY)
                .and()
                .withExternal()
                .source(StateOrder.READY).target(StateOrder.ON_DELIVERY)
                .event(EventOrder.START_DELIVERY)
                .and()
                .withExternal()
                .source(StateOrder.ON_DELIVERY).target(StateOrder.DELIVERED)
                .event(EventOrder.DELIVER)
                .and()
                .withExternal()
                .source(StateOrder.OPENED).target(StateOrder.CANCELLED)
                .event(EventOrder.CANCEL);

    }

}
