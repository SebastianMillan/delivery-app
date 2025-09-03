package com.deliverysl.luxurydelivery.order.repository;

import com.deliverysl.luxurydelivery.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByActivateTrue();
    List<Order> findByActivateFalse();
}
