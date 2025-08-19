package com.deliverysl.luxurydelivery.product.controller;

import com.deliverysl.luxurydelivery.product.dto.CreateFoodDTO;
import com.deliverysl.luxurydelivery.product.dto.FoodDTO;
import com.deliverysl.luxurydelivery.product.mapper.FoodMapper;
import com.deliverysl.luxurydelivery.product.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/food")
public class FoodController implements FoodControllerSwagger{

    private final FoodService service;
    private final FoodMapper mapper;

    @Override
    @PostMapping
    public ResponseEntity<FoodDTO> create(@RequestBody CreateFoodDTO createFoodDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toDto(service.create(createFoodDTO)));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<FoodDTO> edit(@PathVariable Long id, @RequestBody CreateFoodDTO createFoodDTO) {
        return ResponseEntity
                .ok(mapper.toDto(service.edit(id, createFoodDTO)));
    }
}
