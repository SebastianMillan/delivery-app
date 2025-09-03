package com.deliverysl.luxurydelivery.product.controller;

import com.deliverysl.luxurydelivery.product.dto.ProductDTO;
import com.deliverysl.luxurydelivery.product.mapper.ProductMapper;
import com.deliverysl.luxurydelivery.product.model.Product;
import com.deliverysl.luxurydelivery.product.service.ProductService;
import com.deliverysl.luxurydelivery.restaurant.dto.RestaurantDTO;
import com.deliverysl.luxurydelivery.restaurant.model.Restaurant;
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

        List<Product> products = service.findAll();
        return products.isEmpty() ?
               ResponseEntity.noContent().build() :
               ResponseEntity.ok(products.stream().map(mapper::toDto).toList());
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
    //No se si es mas correcto crear un dto especifico para pasarselo en el cuerpo o no
    @PatchMapping("/{id:[0-9]+}/activate")
    @Override
    public ResponseEntity<ProductDTO> activate(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDto(service.activateProduct(id)));
    }

    @GetMapping("/enable")
    @Override
    public ResponseEntity<List<ProductDTO>> findByActivateTrue() {

        List<Product>restaurantList = service.findByActivateTrue();

        return restaurantList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(restaurantList.stream().map(mapper::toDto).toList());
    }

    @GetMapping("/disable")
    @Override
    public ResponseEntity<List<ProductDTO>> findByActivateFalse() {
        List<Product>restaurantList = service.findByActivateFalse();

        return restaurantList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(restaurantList.stream().map(mapper::toDto).toList());
    }
}
