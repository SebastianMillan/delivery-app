package com.deliverysl.luxurydelivery.product.dto;

import java.util.List;

public record CreateDrinkDTO(
        String name,
        String description,
        String image,
        double price,
        Long categoryId,
        List<Long> allergensIdList,
        boolean alcoholic,
        String drinkSize
){}
