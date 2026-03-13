package com.deliverysl.luxurydelivery.product.service;

import com.deliverysl.luxurydelivery.allergen.model.Allergen;
import com.deliverysl.luxurydelivery.product.exception.ProductNotFoundException;
import com.deliverysl.luxurydelivery.product.model.Product;

import com.deliverysl.luxurydelivery.product.repository.ProductRepository;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService extends BaseServiceImpl<Product, Long> {

    private final ProductRepository productRepository;

    public Product findByIdOrThrow(Long id){
        return findOptionalById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Transactional
    public void deactiveProductById(Long id){
        Product product = findByIdOrThrow(id);
        // Desacomplamos con los helper los allergenos asociados a este producto
        for (Allergen allergen: new ArrayList<>(product.getAllergensList())){
            product.removeAllergen(allergen);
        }
        deactivate(id);
    }

}
