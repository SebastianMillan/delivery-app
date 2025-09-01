package com.deliverysl.luxurydelivery.restaurant.dto;

import com.deliverysl.luxurydelivery.category.dto.CategoryDTO;

import java.util.List;

public record RestaurantDTO(
        Long id,
        String name,
        String avatar,
        double rating,
        String type,
        List<CategoryDTO> categoryDTOList,
        boolean activate
        //int employeeNumber
) {
}
