package com.deliverysl.luxurydelivery.order.repository;

import com.deliverysl.luxurydelivery.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
