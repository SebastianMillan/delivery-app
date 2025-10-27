package com.deliverysl.luxurydelivery.allergen.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateAllergenDTO(

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
        String image
){}
