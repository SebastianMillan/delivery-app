package com.deliverysl.luxurydelivery.product.mapper;

import com.deliverysl.luxurydelivery.allergen.mapper.AllergenMapper;
import com.deliverysl.luxurydelivery.category.model.Category;
import com.deliverysl.luxurydelivery.allergen.model.Allergen;
import com.deliverysl.luxurydelivery.product.model.FoodPortion;
import com.deliverysl.luxurydelivery.product.dto.CreateFoodDTO;
import com.deliverysl.luxurydelivery.product.dto.FoodDTO;
import com.deliverysl.luxurydelivery.product.model.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class FoodMapper {

    private final AllergenMapper allergenMapper;

    public FoodDTO toDto(Food food) {
        return new FoodDTO(
                food.getId(),
                food.getName(),
                food.getImage(),
                food.getPrice(),
                food.getCategory().getName(),
                food.getCalories(),
                food.getAllergensList().stream()
                        .map(allergenMapper::toShortDto)
                        .toList()
        );
    }

    public Food toEntity(FoodDTO foodDTO, Category category) {
        return Food.builder()
                .id(foodDTO.id())
                .name(foodDTO.name())
                .image(foodDTO.image())
                .price(foodDTO.price())
                .category(category)
                .calories(foodDTO.calories())
                .allergensList(new ArrayList<>())
                .build();
    }

    public CreateFoodDTO toCreateDto(Food food){
        return new CreateFoodDTO(
                food.getName(),
                food.getDescription(),
                food.getImage(),
                food.getPrice(),
                food.getCategory().getId(),
                food.getAllergensList().stream().map(Allergen::getId).toList(),
                food.getCalories(),
                food.getFoodPortion().name()
        );
    }

    public Food toEntity(CreateFoodDTO createFoodDTO, Category category){
        return Food.builder()
                .name(createFoodDTO.name())
                .description(category.getDescription())
                .image(createFoodDTO.image())
                .price(createFoodDTO.price())
                .category(category)
                .calories(createFoodDTO.calories())
                .allergensList(new ArrayList<>())
                .foodPortion(FoodPortion.valueOf(createFoodDTO.foodPortion()))
                .build();
    }
}
