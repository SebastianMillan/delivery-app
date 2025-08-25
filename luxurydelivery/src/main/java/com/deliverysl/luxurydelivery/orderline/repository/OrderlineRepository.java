package com.deliverysl.luxurydelivery.orderline.repository;

import com.deliverysl.luxurydelivery.orderline.model.Orderline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderlineRepository extends JpaRepository<Orderline,Long> {

}
