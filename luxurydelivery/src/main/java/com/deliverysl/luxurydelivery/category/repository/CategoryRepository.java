package com.deliverysl.luxurydelivery.category.repository;

import com.deliverysl.luxurydelivery.category.model.Category;
import com.deliverysl.luxurydelivery.utils.BaseRepository;

import java.util.Optional;

public interface CategoryRepository extends BaseRepository<Category, Long> {
    Optional<Category> findByRestaurantIdAndNoCategoryTrue(Long idRestaurant);
}
