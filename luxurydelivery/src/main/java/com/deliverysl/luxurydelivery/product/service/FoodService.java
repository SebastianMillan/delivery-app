package com.deliverysl.luxurydelivery.product.service;

import com.deliverysl.luxurydelivery.allergen.model.Allergen;
import com.deliverysl.luxurydelivery.allergen.service.AllergenService;
import com.deliverysl.luxurydelivery.category.model.Category;
import com.deliverysl.luxurydelivery.category.service.CategoryService;
import com.deliverysl.luxurydelivery.product.dto.CreateFoodDTO;
import com.deliverysl.luxurydelivery.product.exception.ProductNotFoundException;
import com.deliverysl.luxurydelivery.product.mapper.FoodMapper;
import com.deliverysl.luxurydelivery.product.model.Food;
import com.deliverysl.luxurydelivery.product.model.FoodPortion;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService extends BaseServiceImpl<Food, Long> {

    private final CategoryService categoryService;
    private final AllergenService allergenService;
    private final FoodMapper mapper;

    @Transactional
    public Food create(CreateFoodDTO createFoodDTO){
        Category category = categoryService.findByIdOrThrow(createFoodDTO.categoryId());
        List<Allergen> allergenList = createFoodDTO.allergensIdList().stream()
                .map(allergenService::findByIdOrThrow)
                .toList();

        Food food = mapper.toEntity(createFoodDTO, category);
        food.setActivate(true);
        category.addProduct(food);

        // Agregamos con los helper los alergenos a este Food
        allergenList.forEach(allergen -> {
            if (allergen.isActivate()){
                food.addAllergen(allergen);
            }
        });

        return save(food);
    }

    @Transactional
    public Food edit(Long id, CreateFoodDTO createFoodDTO){
        Category category = categoryService.findByIdOrThrow(createFoodDTO.categoryId());
        List<Allergen> allergenList = createFoodDTO.allergensIdList().stream()
                .map(allergenService::findByIdOrThrow)
                .toList();

        return findOptionalById(id)
                .map(food -> {
                    // Desacomplamos anteriores alergenos asociados
                    food.getAllergensList().forEach(food::removeAllergen);

                    // Acomplamos los nuevos alergenos
                    allergenList.forEach(food::addAllergen);

                    food.setName(createFoodDTO.name());
                    food.setDescription(createFoodDTO.description());
                    food.setImage(createFoodDTO.image());
                    food.setPrice(createFoodDTO.price());
                    food.setCategory(category);
                    food.setCalories(createFoodDTO.calories());
                    food.setFoodPortion(FoodPortion.valueOf(createFoodDTO.foodPortion()));
                    return save(food);
                }).orElseThrow(() -> new ProductNotFoundException(id));
    }
}
