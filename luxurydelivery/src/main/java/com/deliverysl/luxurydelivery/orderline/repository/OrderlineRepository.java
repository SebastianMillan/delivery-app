package com.deliverysl.luxurydelivery.orderline.repository;

import com.deliverysl.luxurydelivery.orderline.model.Orderline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderlineRepository extends JpaRepository<Orderline,Long> {

    List<Orderline> findByActivateTrueAndOrder_Id(Long idOrder);
    List<Orderline> findByActivateFalseAndOrder_Id(Long idOrder);

}
