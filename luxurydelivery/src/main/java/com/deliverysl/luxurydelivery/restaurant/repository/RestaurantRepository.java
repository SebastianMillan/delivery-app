package com.deliverysl.luxurydelivery.restaurant.repository;

import com.deliverysl.luxurydelivery.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
}
