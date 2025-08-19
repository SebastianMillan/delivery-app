package com.deliverysl.luxurydelivery.product.service;

import com.deliverysl.luxurydelivery.product.exception.ProductNotFoundException;
import com.deliverysl.luxurydelivery.product.model.Product;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService extends BaseServiceImpl<Product, Long> {

    public Product findByIdOrThrow(Long id){
        return findOptionalById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public void deleteProductById(Long id){
        Product product = findByIdOrThrow(id);

        // Desacomplamos con los helper los allergenos asociados a este producto
        product.getAllergensList().forEach(product::removeAllergen);

        delete(product);
    }

}
