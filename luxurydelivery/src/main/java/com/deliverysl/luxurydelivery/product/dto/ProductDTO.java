package com.deliverysl.luxurydelivery.product.dto;

import com.deliverysl.luxurydelivery.allergen.dto.ShortAllergenDTO;

import java.util.List;

public record ProductDTO(
        Long id,
        String name,
        String image,
        double price,
        String category,
        List<ShortAllergenDTO> allergenList
){}
