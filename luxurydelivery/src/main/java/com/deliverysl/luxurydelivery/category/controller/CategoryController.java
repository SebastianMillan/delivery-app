package com.deliverysl.luxurydelivery.category.controller;

import com.deliverysl.luxurydelivery.category.dto.CategoryCreateDTO;
import com.deliverysl.luxurydelivery.category.dto.CategoryDTO;
import com.deliverysl.luxurydelivery.category.mapper.CategoryMapper;
import com.deliverysl.luxurydelivery.category.model.Category;
import com.deliverysl.luxurydelivery.category.service.CategoryService;
import com.deliverysl.luxurydelivery.restaurant.model.Restaurant;
import com.deliverysl.luxurydelivery.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor //Inyección de dependencias
@RequestMapping(
        "category"
)
public class CategoryController
        implements CategoryControllerSwagger {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final RestaurantService restaurantService;

    @GetMapping
    @Override
    public ResponseEntity<List<CategoryDTO>> findAll() {

        List<Category> categoryList = categoryService.findAll();

        return categoryList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(categoryList.stream()
                        .map(categoryMapper::toDto)
                        .toList());

    }

    @GetMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<CategoryDTO>findById(@PathVariable Long id){
        return ResponseEntity.ok(categoryMapper.toDto(categoryService.findByIdOrThrow(id)));
    }

    @PostMapping
    @Override
    public ResponseEntity<CategoryDTO>create(
            @RequestBody CategoryCreateDTO categoryCreateDTO
    ){
        Restaurant restaurant = restaurantService.findByIdOrThrow(categoryCreateDTO.idRestaurant());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryMapper.toDto(categoryService.create(categoryCreateDTO)));
    }

    @PutMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<CategoryDTO>edit(
            @PathVariable Long id,
            @RequestBody CategoryCreateDTO categoryCreateDTO

    ){

        return ResponseEntity.ok(categoryMapper.toDto(categoryService.edit(categoryCreateDTO,id)));
    }

    @DeleteMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<?> delete(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id:[0-9]+}/activate")
    @Override
    public ResponseEntity<CategoryDTO> activate(@PathVariable Long id) {
        return ResponseEntity.ok(categoryMapper.toDto(categoryService.activate(id)));
    }

    @GetMapping("/enable")
    @Override
    public ResponseEntity<List<CategoryDTO>> findByActivateTrue() {
        List<Category> categoryList = categoryService.findByActivateTrue();
        return categoryList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(categoryList.stream().map(categoryMapper::toDto).toList());
    }

    @GetMapping("/disable")
    @Override
    public ResponseEntity<List<CategoryDTO>> findByActivateFalse() {
        List<Category> categoryList = categoryService.findByActivateFalse();
        return categoryList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(categoryList.stream().map(categoryMapper::toDto).toList());
    }


}
