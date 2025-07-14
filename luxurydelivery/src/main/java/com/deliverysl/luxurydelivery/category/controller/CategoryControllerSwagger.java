package com.deliverysl.luxurydelivery.category.controller;

import com.deliverysl.luxurydelivery.category.dto.CategoryCreateDTO;
import com.deliverysl.luxurydelivery.category.dto.CategoryDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryControllerSwagger {

    ResponseEntity<List<CategoryDTO>> findAll();
    ResponseEntity<CategoryDTO> findById(Long id);
    ResponseEntity<CategoryCreateDTO> create(CategoryCreateDTO categoryCreateDTO);
    ResponseEntity<CategoryCreateDTO> edit(CategoryCreateDTO categoryCreateDTO, Long id);
    ResponseEntity<?> delete(Long id);


}
