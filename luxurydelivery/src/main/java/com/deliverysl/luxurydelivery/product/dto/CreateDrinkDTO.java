package com.deliverysl.luxurydelivery.product.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record CreateDrinkDTO(
        @NotBlank(message = "Name cannot be empty")
        String name,
        @NotBlank(message = "Description cannot be empty")
        String description,
        @NotBlank(message = "Image url cannot be empty")
        @Pattern(
                message = ("Image must be .png,.jpg,.jpeg"),
                /* ^(https?://).+\\. */ //Dejo esto comentado porque no si lo añadiremos al patron
                //Ya que no se si cogeremos las imagenes de internet, se subiran a algun servidor, o solo sera local
                //Lo dejo basico y luego modificamos
                regexp = "^.+\\.(png|jpg|jpeg)$"
        )
        String image,
        @NotNull(message = "Price cannot be null")
        @Positive(message = "Price cannot be 0 or negative")
        @Digits(integer = 10, fraction = 2, message = "Price must have exactly 2 decimal")
        BigDecimal price,
        @NotNull(message = "CategoryId cannot be null")
        Long categoryId,
        List<Long> allergensIdList,
        boolean alcoholic,
        @NotBlank(message = "DrinkSize cannot be empty")
        String drinkSize
){}
