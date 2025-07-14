package com.deliverysl.luxurydelivery.category.controller;

import com.deliverysl.luxurydelivery.category.dto.CategoryCreateDTO;
import com.deliverysl.luxurydelivery.category.dto.CategoryDTO;
import com.deliverysl.luxurydelivery.category.mapper.CategoryMapper;
import com.deliverysl.luxurydelivery.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor //Inyección de dependencias
@RequestMapping(
        "/category"
)
public class CategoryController
        implements CategoryControllerSwagger {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    @Override
    public ResponseEntity<List<CategoryDTO>> findAll() {

        return categoryService.findAll().isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(categoryService.findAll()
                .stream().map(categoryMapper::toDto)
                .toList());

    }

    @GetMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<CategoryDTO>findById(@PathVariable Long id){
        return ResponseEntity.ok(categoryMapper.toDto(categoryService.findById(id)));
    }

    @PostMapping
    @Override
    public ResponseEntity<CategoryCreateDTO>create(
            @RequestBody CategoryCreateDTO categoryCreateDTO
    ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryMapper.toCreateDto(categoryService.create(categoryCreateDTO)));
    }

    @PutMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<CategoryCreateDTO>edit(
            @RequestBody CategoryCreateDTO categoryCreateDTO,
            @PathVariable Long id
    ){
        return ResponseEntity.ok(categoryMapper.toCreateDto(categoryService.edit(categoryCreateDTO,id)));
    }

    @DeleteMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<?> delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }




}
