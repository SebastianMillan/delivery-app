package com.deliverysl.luxurydelivery.type.repository;

import com.deliverysl.luxurydelivery.type.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type,Long> {

    Optional<Type> findByName(String name);
    List<Type> findByActivateTrue();
    List<Type> findByActivateFalse();
}
