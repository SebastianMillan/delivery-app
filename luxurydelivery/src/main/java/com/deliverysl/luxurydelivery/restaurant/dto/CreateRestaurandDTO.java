package com.deliverysl.luxurydelivery.restaurant.dto;

import com.deliverysl.luxurydelivery.category.dto.CategoryCreateDTO;

import java.util.List;

public record CreateRestaurandDTO(
        String name,
        String avatar,
        double rating,
        String type,
        List<CategoryCreateDTO> categoryDTOList
        //List<EmployeeCreateDTO> employeeCreateDTOList
) {
}
