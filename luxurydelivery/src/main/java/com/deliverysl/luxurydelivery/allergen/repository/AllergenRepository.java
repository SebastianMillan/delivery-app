package com.deliverysl.luxurydelivery.allergen.repository;

import com.deliverysl.luxurydelivery.allergen.model.Allergen;
import com.deliverysl.luxurydelivery.type.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllergenRepository extends JpaRepository<Allergen, Long> {
    List<Allergen> findByActivateTrue();
    List<Allergen> findByActivateFalse();
}
