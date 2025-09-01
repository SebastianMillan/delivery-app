package com.deliverysl.luxurydelivery.restaurant.controller;

import com.deliverysl.luxurydelivery.restaurant.dto.CreateRestaurandDTO;
import com.deliverysl.luxurydelivery.restaurant.dto.RestaurantDTO;
import com.deliverysl.luxurydelivery.restaurant.mapper.RestaurantMapper;
import com.deliverysl.luxurydelivery.restaurant.model.Restaurant;
import com.deliverysl.luxurydelivery.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController
        implements RestaurantControllerSwagger{

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    @GetMapping
    @Override
    public ResponseEntity<List<RestaurantDTO>> findAll(){

        List<Restaurant> restaurantList = restaurantService.findAll();

        return restaurantList.isEmpty() ?
               ResponseEntity.noContent().build() :
               ResponseEntity.ok(restaurantList.stream().map(restaurantMapper::toDto).toList());

    }

    @GetMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<RestaurantDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(restaurantMapper.toDto(restaurantService.findByIdOrThrow(id)));
    }


    @PostMapping
    @Override
    public ResponseEntity<RestaurantDTO> create(@RequestBody  CreateRestaurandDTO createRestaurandDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantMapper.toDto(restaurantService.create(createRestaurandDTO)));
    }

    @PutMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<RestaurantDTO>edit(@PathVariable Long id,@RequestBody CreateRestaurandDTO createRestaurandDTO){

        return ResponseEntity.ok(restaurantMapper.toDto(restaurantService.edit(createRestaurandDTO,id)));

    }

    @DeleteMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<?>delete(@PathVariable Long id){
       restaurantService.deleteRestaurantById(id);
       return ResponseEntity.ok().build();
    }

    //No se si es mas correcto crear un dto especifico para pasarselo en el cuerpo o no
    @PatchMapping("/{id:[0-9]+}/activate")
    @Override
    public ResponseEntity<RestaurantDTO> activate(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantMapper.toDto(restaurantService.activateRestaurant(id)));
    }

    @GetMapping("/enable")
    @Override
    public ResponseEntity<List<RestaurantDTO>> findByActivateTrue() {

        List<Restaurant>restaurantList = restaurantService.findByActivateTrue();

        return restaurantList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(restaurantList.stream().map(restaurantMapper::toDto).toList());
    }

    @GetMapping("/disable")
    @Override
    public ResponseEntity<List<RestaurantDTO>> findByActivateFalse() {
        List<Restaurant>restaurantList = restaurantService.findByActivateFalse();

        return restaurantList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(restaurantList.stream().map(restaurantMapper::toDto).toList());
    }

}
