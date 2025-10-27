package com.deliverysl.luxurydelivery.restaurant.repository;

import com.deliverysl.luxurydelivery.restaurant.model.Restaurant;
import com.deliverysl.luxurydelivery.utils.BaseRepository;

import java.util.List;

public interface RestaurantRepository extends BaseRepository<Restaurant,Long> {

    List<Restaurant> findByType_id(Long typeId);

}
