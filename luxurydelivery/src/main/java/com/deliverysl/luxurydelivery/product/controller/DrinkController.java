package com.deliverysl.luxurydelivery.product.controller;

import com.deliverysl.luxurydelivery.product.dto.CreateDrinkDTO;
import com.deliverysl.luxurydelivery.product.dto.DrinkDTO;
import com.deliverysl.luxurydelivery.product.mapper.DrinkMapper;
import com.deliverysl.luxurydelivery.product.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drink")
public class DrinkController implements DrinkControllerSwagger{

    private final DrinkService service;
    private final DrinkMapper mapper;

    @Override
    @PostMapping
    public ResponseEntity<DrinkDTO> create(@RequestBody CreateDrinkDTO createDrinkDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toDto(service.create(createDrinkDTO)));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<DrinkDTO> edit(@PathVariable Long id, @RequestBody CreateDrinkDTO createDrinkDTO) {
        return ResponseEntity
                .ok(mapper.toDto(service.edit(id, createDrinkDTO)));
    }
}
