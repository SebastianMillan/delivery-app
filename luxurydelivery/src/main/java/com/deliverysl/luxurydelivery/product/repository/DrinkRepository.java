package com.deliverysl.luxurydelivery.product.repository;

import com.deliverysl.luxurydelivery.product.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
}
