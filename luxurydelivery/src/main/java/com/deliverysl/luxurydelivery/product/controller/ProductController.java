package com.deliverysl.luxurydelivery.product.controller;

import com.deliverysl.luxurydelivery.product.dto.ProductDTO;
import com.deliverysl.luxurydelivery.product.exception.ProductNotFoundException;
import com.deliverysl.luxurydelivery.product.mapper.ProductMapper;
import com.deliverysl.luxurydelivery.product.model.Product;
import com.deliverysl.luxurydelivery.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController implements ProductControllerSwagger{

    private final ProductService service;
    private final ProductMapper mapper;

    @Override
    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        // Solución igualmente válida, aunque se entiende peor y es menos mantenible. A veces las cosas simples son la mejor opción
        /*
        List<Product> products = service.findAll();
        return products.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(products.stream()
                    .map(productMapper::toDto)
                    .toList());
         */

        List<Product> products = service.findAll();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(
                products.stream()
                        .map(mapper::toDto)
                        .toList()
        );
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDto(service.findByIdOrThrow(id)));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
