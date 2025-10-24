package com.deliverysl.luxurydelivery.restaurant.dto;

import jakarta.validation.constraints.*;

public record CreateRestaurandDTO(
        @NotBlank(message = "Name cannot be empty")
        String name,
        @NotBlank(message = "Avatar url cannot be empty")
        @Pattern(
                message = ("Avatar must be .png,.jpg,.jpeg"),
                /* ^(https?://).+\\. */ //Dejo esto comentado porque no si lo añadiremos al patron
                //Ya que no se si cogeremos las imagenes de internet, se subiran a algun servidor, o solo sera local
                //Lo dejo basico y luego modificamos
                regexp = "^.+\\.(png|jpg|jpeg)$"
        )
        String avatar,
        @Digits(integer = 1,fraction = 1,message = "Rating must be a number with a maximum of 1 integer digit and 1 fractional digits")
        @Min(value = 0,message = "Rating cannot be less than 0")
        @Max(value = 5,message = "Rating cannot be higher than 5")
        double rating,
        @NotBlank(message = "Type cannot be empty")
        String type
        //List<CategoryCreateDTO> categoryDTOList
        //List<EmployeeCreateDTO> employeeCreateDTOList
) {
}
