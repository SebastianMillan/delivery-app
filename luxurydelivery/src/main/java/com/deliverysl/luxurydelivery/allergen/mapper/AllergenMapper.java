package com.deliverysl.luxurydelivery.allergen.mapper;

import com.deliverysl.luxurydelivery.allergen.dto.AllergenDTO;
import com.deliverysl.luxurydelivery.allergen.dto.CreateAllergenDTO;
import com.deliverysl.luxurydelivery.allergen.dto.ShortAllergenDTO;
import com.deliverysl.luxurydelivery.allergen.model.Allergen;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AllergenMapper {

    public AllergenDTO toDto(Allergen allergen){
        return new AllergenDTO(
                allergen.getId(),
                allergen.getName(),
                allergen.getDescription(),
                allergen.getImage(),
                allergen.isActivate()
        );
    }

    public Allergen toEntity(AllergenDTO allergenDTO){
        return Allergen.builder()
                .id(allergenDTO.id())
                .name(allergenDTO.name())
                .description(allergenDTO.description())
                .image(allergenDTO.image())
                .productList(new ArrayList<>())
                .activate(allergenDTO.activate())
                .build();
    }

    public CreateAllergenDTO toCreateDto(Allergen allergen){
        return new CreateAllergenDTO(
                allergen.getName(),
                allergen.getDescription(),
                allergen.getImage()
        );
    }

    public Allergen toEntity(CreateAllergenDTO createAllergenDTO){
        return Allergen.builder()
                .name(createAllergenDTO.name())
                .description(createAllergenDTO.description())
                .image(createAllergenDTO.image())
                .productList(new ArrayList<>())
                .build();
    }

    public ShortAllergenDTO toShortDto(Allergen allergen){
        return new ShortAllergenDTO(
                allergen.getName(),
                allergen.getImage()
        );
    }

    public Allergen toEntity(ShortAllergenDTO shortAllergenDTO){
        return Allergen.builder()
                .name(shortAllergenDTO.name())
                .image(shortAllergenDTO.image())
                .productList(new ArrayList<>())
                .build();
    }
}

