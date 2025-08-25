package com.deliverysl.luxurydelivery.restaurant.mapper;

import com.deliverysl.luxurydelivery.restaurant.dto.RestaurantDTO;
import com.deliverysl.luxurydelivery.restaurant.model.Restaurant;

import com.deliverysl.luxurydelivery.type.model.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantMapper {

    public RestaurantDTO toDto(Restaurant restaurant){
        return new RestaurantDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAvatar(),
                restaurant.getRating(),
                restaurant.getType().getName()
                //restaurant.getEmployeeList().size()
        );
    }

    public Restaurant toEntity(RestaurantDTO restaurantDTO, Type type){

        return Restaurant.builder()
                .id(restaurantDTO.id())
                .name(restaurantDTO.name())
                .avatar(restaurantDTO.avatar())
                .rating(restaurantDTO.rating())
                .type(type)
                .build();
    }
}
