package com.deliverysl.luxurydelivery.type.controller;

import com.deliverysl.luxurydelivery.type.dto.TypeCreateDTO;
import com.deliverysl.luxurydelivery.type.dto.TypeDTO;
import com.deliverysl.luxurydelivery.type.mapper.TypeMapper;
import com.deliverysl.luxurydelivery.type.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(
        "/type"
)
public class TypeController
        implements TypeControllerSwagger {

    private final TypeService typeService;
    private final TypeMapper typeMapper;

    @GetMapping
    @Override
    public ResponseEntity<List<TypeDTO>> findAll(){

        return typeService.findAll().isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(typeService.findAll()
                .stream().map(typeMapper::toDto)
                .toList());

    }

    @GetMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<TypeDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(typeMapper.toDto(typeService.findById(id)));
    }

    @PostMapping
    @Override
    public ResponseEntity<TypeDTO>create(@RequestBody TypeCreateDTO typeCreateDTO){

        return ResponseEntity.status(HttpStatus.CREATED).body(
                typeMapper.toDto(typeService.create(typeMapper.toEntity(typeCreateDTO))));
    }

    @PutMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<TypeDTO>edit(@RequestBody TypeCreateDTO typeCreateDTO,@PathVariable Long id){
        return ResponseEntity.ok(typeMapper.toDto(typeService.edit(typeMapper.toEntity(typeCreateDTO),id)));
    }

    @DeleteMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<?> delete(@PathVariable Long id){
        typeService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
