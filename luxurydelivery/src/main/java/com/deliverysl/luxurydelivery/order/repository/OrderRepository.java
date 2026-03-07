package com.deliverysl.luxurydelivery.order.repository;

import com.deliverysl.luxurydelivery.order.model.Order;
import com.deliverysl.luxurydelivery.order.statemachine.state.StateOrder;
import com.deliverysl.luxurydelivery.utils.BaseRepository;
import org.aspectj.weaver.ast.Or;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends BaseRepository<Order,Long> {
    //Buscamos en la BD si hay algun pedido abierto y activo para un cliente
    boolean existsByStateOrderAndClient_IdAndActiveTrue(StateOrder state,Long idClient);

    boolean existsByRider_idAndStateOrder(Long idRider,StateOrder state);

}
