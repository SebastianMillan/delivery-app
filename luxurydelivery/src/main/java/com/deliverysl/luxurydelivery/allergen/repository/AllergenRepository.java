package com.deliverysl.luxurydelivery.allergen.repository;

import com.deliverysl.luxurydelivery.allergen.model.Allergen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergenRepository extends JpaRepository<Allergen, Long> {
}
