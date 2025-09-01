package com.deliverysl.luxurydelivery.type.controller;

import com.deliverysl.luxurydelivery.type.dto.TypeCreateDTO;
import com.deliverysl.luxurydelivery.type.dto.TypeDTO;
import com.deliverysl.luxurydelivery.type.mapper.TypeMapper;
import com.deliverysl.luxurydelivery.type.model.Type;
import com.deliverysl.luxurydelivery.type.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/type")
public class TypeController
        implements TypeControllerSwagger {

    private final TypeService typeService;
    private final TypeMapper typeMapper;

    @GetMapping
    @Override
    public ResponseEntity<List<TypeDTO>> findAll(){
        List<Type> typeList = typeService.findAll();

        return typeList.isEmpty() ?
               ResponseEntity.noContent().build() :
               ResponseEntity.ok(typeList.stream()
                       .map(typeMapper::toDto)
                       .toList());
    }

    @GetMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<TypeDTO> findById(@PathVariable Long id){
       return ResponseEntity.ok(typeMapper.toDto(typeService.findByIdOrThrow(id)));
    }

    @PostMapping
    @Override
    public ResponseEntity<TypeDTO>create(@RequestBody TypeCreateDTO typeCreateDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(typeMapper.toDto(typeService.create(typeCreateDTO)));
    }

    @PutMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<TypeDTO>edit(@PathVariable Long id,@RequestBody TypeCreateDTO typeCreateDTO){
        return ResponseEntity.ok(typeMapper.toDto(typeService.edit(typeCreateDTO,id)));
    }

    @DeleteMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<?> delete(@PathVariable Long id){
        typeService.deleteTypeById(id);
        return ResponseEntity.noContent().build();
    }

    //No se si es mas correcto crear un dto especifico para pasarselo en el cuerpo o no
    @PatchMapping("/{id:[0-9]+}/activate")
    @Override
    public ResponseEntity<TypeDTO> activate(@PathVariable Long id) {
        return ResponseEntity.ok(typeMapper.toDto(typeService.activateType(id)));
    }

    @GetMapping("/enable")
    @Override
    public ResponseEntity<List<TypeDTO>> findByActivateTrue() {

        List<Type>typeList = typeService.findByActivateTrue();

        return typeList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(typeList.stream().map(typeMapper::toDto).toList());
    }

    @GetMapping("/disable")
    @Override
    public ResponseEntity<List<TypeDTO>> findByActivateFalse() {
        List<Type>typeList = typeService.findByActivateFalse();

        return typeList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(typeList.stream().map(typeMapper::toDto).toList());
    }

}
