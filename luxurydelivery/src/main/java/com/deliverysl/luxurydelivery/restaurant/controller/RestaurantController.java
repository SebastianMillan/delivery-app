package com.deliverysl.luxurydelivery.restaurant.controller;

import com.deliverysl.luxurydelivery.restaurant.dto.RestaurantDTO;
import com.deliverysl.luxurydelivery.restaurant.mapper.RestaurantMapper;
import com.deliverysl.luxurydelivery.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(
        "/restaurant"
)
public class RestaurantController
        implements RestaurantControllerSwagger{

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    @GetMapping
    @Override
    public ResponseEntity<List<RestaurantDTO>> findAll(){

        return restaurantService.findAll().isEmpty() ?
               ResponseEntity.noContent().build() :
               ResponseEntity.ok(restaurantService.findAll()
               .stream().map(restaurantMapper::toDto)
               .toList());

    }

    @GetMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<RestaurantDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(restaurantMapper.toDto(restaurantService.findById(id)));
    }


    @PostMapping
    @Override
    public ResponseEntity<RestaurantDTO> create(@RequestBody RestaurantDTO restaurantDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantMapper.toDto(restaurantService.create(restaurantDTO)));
    }

    @PutMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<RestaurantDTO>edit(@RequestBody RestaurantDTO restaurantDTO,@PathVariable Long id){

        return ResponseEntity.ok(restaurantMapper.toDto(restaurantService.edit(restaurantDTO,id)));

    }

    @DeleteMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<?>delete(@PathVariable Long id){
       restaurantService.delete(id);
       return ResponseEntity.ok().build();
    }




}
