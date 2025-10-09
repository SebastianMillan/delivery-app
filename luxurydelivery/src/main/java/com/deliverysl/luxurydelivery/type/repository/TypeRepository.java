package com.deliverysl.luxurydelivery.type.repository;

import com.deliverysl.luxurydelivery.type.model.Type;
import com.deliverysl.luxurydelivery.utils.BaseRepository;

import java.util.Optional;

public interface TypeRepository extends BaseRepository<Type,Long> {

    Optional<Type> findByName(String name);
}
