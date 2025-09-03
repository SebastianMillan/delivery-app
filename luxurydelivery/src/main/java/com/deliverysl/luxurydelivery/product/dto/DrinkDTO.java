package com.deliverysl.luxurydelivery.product.dto;

import com.deliverysl.luxurydelivery.allergen.dto.ShortAllergenDTO;

import java.math.BigDecimal;
import java.util.List;

public record DrinkDTO(
        Long id,
        String name,
        String image,
        BigDecimal price,
        String category,
        String drinkSize,
        List<ShortAllergenDTO> allergenList,
        boolean active
) {}
