package com.deliverysl.luxurydelivery.orderline.repository;

import com.deliverysl.luxurydelivery.orderline.model.Orderline;
import com.deliverysl.luxurydelivery.utils.BaseRepository;

import java.util.Optional;

public interface OrderlineRepository extends BaseRepository<Orderline,Long> {
    //Busqueda de la linea de pedido por el pedido y el producto
    Optional<Orderline> findByOrderIdAndProductId(Long idOrder,Long idProduct);
    //Buscamos si existen lineas de pedido activadas
    boolean existsByOrder_IdAndActiveTrue(Long idOrder);
}
