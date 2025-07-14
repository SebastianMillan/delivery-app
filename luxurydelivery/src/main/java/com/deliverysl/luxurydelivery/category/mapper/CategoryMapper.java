package com.deliverysl.luxurydelivery.category.mapper;

import com.deliverysl.luxurydelivery.category.dto.CategoryDTO;
import com.deliverysl.luxurydelivery.category.dto.CategoryCreateDTO;
import com.deliverysl.luxurydelivery.category.model.Category;
import com.deliverysl.luxurydelivery.restaurant.model.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryDTO categoryDTO){
        return Category.builder()
                .id(categoryDTO.id())
                .name(categoryDTO.name())
                .description(categoryDTO.description())
                .build();
    }

    public CategoryDTO toDto(Category category){
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    public Category toEntity(CategoryCreateDTO createCategoryDTO, Restaurant restaurant){
        return Category.builder()
                .name(createCategoryDTO.name())
                .description(createCategoryDTO.description())
                .restaurant(restaurant)
                .build();
    }

    public CategoryCreateDTO toCreateDto(Category category){
        return new CategoryCreateDTO(
                category.getName(),
                category.getDescription(),
                category.getRestaurant().getId()
        );
    }


}
