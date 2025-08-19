package com.deliverysl.luxurydelivery.allergen.controller;

import com.deliverysl.luxurydelivery.allergen.dto.AllergenDTO;
import com.deliverysl.luxurydelivery.allergen.dto.CreateAllergenDTO;
import com.deliverysl.luxurydelivery.allergen.mapper.AllergenMapper;
import com.deliverysl.luxurydelivery.allergen.model.Allergen;
import com.deliverysl.luxurydelivery.allergen.service.AllergenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/allergen")
public class AllergenController implements AllergenControllerSwagger{

    private final AllergenService service;
    private final AllergenMapper mapper;

    @Override
    @GetMapping
    public ResponseEntity<List<AllergenDTO>> findAll() {
        List<Allergen> allergens = service.findAll();
        if(allergens.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(
                allergens.stream()
                        .map(mapper::toDto)
                        .toList()
        );
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AllergenDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDto(service.findByIdOrThrow(id)));
    }

    @Override
    @PostMapping
    public ResponseEntity<AllergenDTO> create(@RequestBody CreateAllergenDTO createAllergenDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toDto(service.create(createAllergenDTO)));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<AllergenDTO> edit(@PathVariable Long id, @RequestBody CreateAllergenDTO createAllergenDTO) {
        return ResponseEntity.ok(mapper.toDto(service.edit(id, createAllergenDTO)));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteAllergenById(id);
        return ResponseEntity.noContent().build();
    }
}
