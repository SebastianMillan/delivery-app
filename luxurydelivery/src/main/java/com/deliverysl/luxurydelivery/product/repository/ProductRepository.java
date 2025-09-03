package com.deliverysl.luxurydelivery.product.repository;

import com.deliverysl.luxurydelivery.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByActivateTrue();
    List<Product> findByActivateFalse();
}
