package com.deliverysl.luxurydelivery.product.dto;

import java.math.BigDecimal;
import java.util.List;

public record CreateDrinkDTO(
        String name,
        String description,
        String image,
        BigDecimal price,
        Long categoryId,
        List<Long> allergensIdList,
        boolean alcoholic,
        String drinkSize
){}
