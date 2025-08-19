package com.deliverysl.luxurydelivery.product.dto;

import java.util.List;

public record CreateFoodDTO(
        String name,
        String description,
        String image,
        double price,
        Long categoryId,
        List<Long> allergensIdList,
        int calories,
        String foodPortion
){}
