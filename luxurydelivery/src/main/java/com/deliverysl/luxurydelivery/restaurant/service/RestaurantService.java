package com.deliverysl.luxurydelivery.restaurant.service;

import com.deliverysl.luxurydelivery.category.model.Category;
import com.deliverysl.luxurydelivery.restaurant.dto.CreateRestaurandDTO;
import com.deliverysl.luxurydelivery.restaurant.exception.RestaurantNotFoundException;
import com.deliverysl.luxurydelivery.restaurant.mapper.RestaurantMapper;
import com.deliverysl.luxurydelivery.restaurant.model.Restaurant;
import com.deliverysl.luxurydelivery.restaurant.repository.RestaurantRepository;
import com.deliverysl.luxurydelivery.type.model.Type;
import com.deliverysl.luxurydelivery.type.service.TypeService;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService extends BaseServiceImpl<Restaurant,Long> {

    private final TypeService typeService;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public Restaurant findByIdOrThrow(Long id){
        return findOptionalById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    @Transactional
    public Restaurant create(CreateRestaurandDTO createRestaurandDTO){

        Type type = typeService.findByName(createRestaurandDTO.type());

        Restaurant restaurant = restaurantMapper.toEntity(createRestaurandDTO,type);
        restaurant.setActivate(true);

        type.addRestaurant(restaurant);

        //el restaurante que se cree tendrá un categoría por defecto
        Category category = Category.categoryDefault(restaurant);
        restaurant.addCategory(category);

        return save(restaurant);
    }

    @Transactional
    public Restaurant edit(CreateRestaurandDTO createRestaurandDTO,Long id){

        Type type = typeService.findByName(createRestaurandDTO.type());
        Restaurant restaurant = findByIdOrThrow(id);
        restaurant.setName(createRestaurandDTO.name());
        restaurant.setAvatar(createRestaurandDTO.avatar());
        restaurant.setRating(createRestaurandDTO.rating());
        restaurant.setType(type);

        return save(restaurant);

    }

    @Transactional
    public void deleteRestaurantById(Long id){
        Restaurant restaurant = findByIdOrThrow(id);
        restaurant.setActivate(false);
        save(restaurant);
    }

    @Transactional
    public Restaurant activateRestaurant(Long id){
        Restaurant restaurant = findByIdOrThrow(id);
        if (!restaurant.isActivate()){
            restaurant.setActivate(true);
            save(restaurant);
        };
        return restaurant;
    }

    public List<Restaurant> findByActivateTrue(){
        return restaurantRepository.findByActivateTrue();
    }

    public List<Restaurant> findByActivateFalse(){
        return restaurantRepository.findByActivateFalse();
    }

}
