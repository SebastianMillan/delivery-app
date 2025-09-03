package com.deliverysl.luxurydelivery.product.mapper;

import com.deliverysl.luxurydelivery.allergen.mapper.AllergenMapper;
import com.deliverysl.luxurydelivery.category.model.Category;
import com.deliverysl.luxurydelivery.allergen.model.Allergen;
import com.deliverysl.luxurydelivery.product.model.DrinkSize;
import com.deliverysl.luxurydelivery.product.dto.CreateDrinkDTO;
import com.deliverysl.luxurydelivery.product.dto.DrinkDTO;
import com.deliverysl.luxurydelivery.product.model.Drink;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class DrinkMapper {

    private final AllergenMapper allergenMapper;

    public DrinkDTO toDto(Drink drink) {
        return new DrinkDTO(
                drink.getId(),
                drink.getName(),
                drink.getImage(),
                drink.getPrice(),
                drink.getCategory().getName(),
                drink.getDrinkSize().name(),
                drink.getAllergensList().stream()
                        .map(allergenMapper::toShortDto)
                        .toList(),
                drink.isActivate()
        );
    }

    public Drink toEntity(DrinkDTO drinkDTO, Category category) {
        return Drink.builder()
                .id(drinkDTO.id())
                .name(drinkDTO.name())
                .image(drinkDTO.image())
                .price(drinkDTO.price())
                .category(category)
                .allergensList(new ArrayList<>())
                .drinkSize(DrinkSize.valueOf(drinkDTO.drinkSize()))
                .activate(drinkDTO.active())
                .build();
    }

    public CreateDrinkDTO toCreateDto(Drink drink){
        return new CreateDrinkDTO(
                drink.getName(),
                drink.getDescription(),
                drink.getImage(),
                drink.getPrice(),
                drink.getCategory().getId(),
                drink.getAllergensList().stream()
                        .map(Allergen::getId)
                        .toList(),
                drink.isAlcoholic(),
                drink.getDrinkSize().name()
        );
    }

    public Drink toEntity(CreateDrinkDTO createDrinkDTO, Category category){
        return Drink.builder()
                .name(createDrinkDTO.name())
                .description(category.getDescription())
                .image(createDrinkDTO.image())
                .price(createDrinkDTO.price())
                .category(category)
                .alcoholic(createDrinkDTO.alcoholic())
                .allergensList(new ArrayList<>())
                .drinkSize(DrinkSize.valueOf(createDrinkDTO.drinkSize()))
                .build();
    }
}
