package com.deliverysl.luxurydelivery.product.repository;

import com.deliverysl.luxurydelivery.product.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
