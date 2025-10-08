package com.deliverysl.luxurydelivery.restaurant.service;

import com.deliverysl.luxurydelivery.category.model.Category;
import com.deliverysl.luxurydelivery.common.DefaultsIds;
import com.deliverysl.luxurydelivery.restaurant.dto.CreateRestaurandDTO;
import com.deliverysl.luxurydelivery.restaurant.exception.RestaurantNotFoundException;
import com.deliverysl.luxurydelivery.restaurant.mapper.RestaurantMapper;
import com.deliverysl.luxurydelivery.restaurant.model.Restaurant;
import com.deliverysl.luxurydelivery.type.model.Type;
import com.deliverysl.luxurydelivery.type.service.TypeService;
import com.deliverysl.luxurydelivery.user.model.Employee;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService extends BaseServiceImpl<Restaurant,Long> {

    private final TypeService typeService;
    private final RestaurantMapper restaurantMapper;

    public Restaurant findByIdOrThrow(Long id){
        return findOptionalById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    @Transactional
    public Restaurant create(CreateRestaurandDTO createRestaurandDTO){

        Type type = typeService.findByName(createRestaurandDTO.type());

        Restaurant restaurant = restaurantMapper.toEntity(createRestaurandDTO,type);
        restaurant.setActive(true);

        type.addRestaurant(restaurant);

        //el restaurante que se cree tendrá un categoría por defecto
        Category category = Category.builder()
                .name("Sin categoría")
                .description("Para productos sin categoría seleccionada")
                .restaurant(restaurant)
                .build();

        restaurant.addCategory(category);
        
        return save(restaurant);
    }

    @Transactional
    public Restaurant edit(CreateRestaurandDTO createRestaurandDTO,Long id){

        if (id == DefaultsIds.DEFAULT_RESTAURANT_ID){throw new RestaurantNotFoundException(id);}

        Type type = typeService.findByName(createRestaurandDTO.type());
        Restaurant restaurant = findByIdOrThrow(id);
        restaurant.setName(createRestaurandDTO.name());
        restaurant.setAvatar(createRestaurandDTO.avatar());
        restaurant.setRating(createRestaurandDTO.rating());
        restaurant.setType(type);

        return save(restaurant);

    }

    @Transactional
    public void deactiveRestaurantById(Long id){

        if (id == DefaultsIds.DEFAULT_RESTAURANT_ID){throw new RestaurantNotFoundException(id);}

        Restaurant defaultRestaurant = findByIdOrThrow(DefaultsIds.DEFAULT_RESTAURANT_ID);
        Restaurant restaurant = findByIdOrThrow(id);

        defaultRestaurant.getEmployeeList().addAll(restaurant.getEmployeeList());

        for(Employee employee:restaurant.getEmployeeList()){
            employee.setRestaurant(defaultRestaurant);
        }

        save(defaultRestaurant);
        deactivate(id);
    }
}
