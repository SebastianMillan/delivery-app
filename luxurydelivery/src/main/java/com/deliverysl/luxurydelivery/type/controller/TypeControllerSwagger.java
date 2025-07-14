package com.deliverysl.luxurydelivery.type.controller;

import com.deliverysl.luxurydelivery.type.dto.TypeCreateDTO;
import com.deliverysl.luxurydelivery.type.dto.TypeDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TypeControllerSwagger {

    ResponseEntity<List<TypeDTO>> findAll();
    ResponseEntity<TypeDTO> findById(Long id);
    ResponseEntity<TypeDTO> create(TypeCreateDTO typeCreateDTO);
    ResponseEntity<TypeDTO> edit(TypeCreateDTO typeCreateDTO,Long id);
    ResponseEntity<?>delete(Long id);
}
