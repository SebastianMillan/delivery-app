package com.deliverysl.luxurydelivery.restaurant.mapper;

import com.deliverysl.luxurydelivery.category.mapper.CategoryMapper;
import com.deliverysl.luxurydelivery.restaurant.dto.CreateRestaurandDTO;
import com.deliverysl.luxurydelivery.restaurant.dto.RestaurantDTO;
import com.deliverysl.luxurydelivery.restaurant.model.Restaurant;

import com.deliverysl.luxurydelivery.type.model.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class RestaurantMapper {

    private final CategoryMapper categoryMapper;

    public RestaurantDTO toDto(Restaurant restaurant){
        return new RestaurantDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAvatar(),
                restaurant.getRating(),
                restaurant.getType().getName(),
                restaurant.getCategoryList().stream()
                        .map(categoryMapper::toDto)
                        .toList(),
                restaurant.isActivate(),
                restaurant.getEmployeeList().size()
        );
    }

    public Restaurant toEntity(RestaurantDTO restaurantDTO, Type type){

        return Restaurant.builder()
                .id(restaurantDTO.id())
                .name(restaurantDTO.name())
                .avatar(restaurantDTO.avatar())
                .rating(restaurantDTO.rating())
                .type(type)
                .categoryList(new ArrayList<>())
                .employeeList(new ArrayList<>())
                .activate(restaurantDTO.activate())
                .build();
    }

    public CreateRestaurandDTO toCreateDto(Restaurant restaurant){
        return new CreateRestaurandDTO(
                restaurant.getName(),
                restaurant.getAvatar(),
                restaurant.getRating(),
                restaurant.getType().getName(),
                restaurant.getCategoryList().stream()
                        .map(categoryMapper::toCreateDto)
                        .toList()
        );
    }

    public Restaurant toEntity(CreateRestaurandDTO createRestaurandDTO,Type type){

        return Restaurant.builder()
                .name(createRestaurandDTO.name())
                .avatar(createRestaurandDTO.avatar())
                .rating(createRestaurandDTO.rating())
                .type(type)
                .categoryList(new ArrayList<>())
                .build();
    }
}
