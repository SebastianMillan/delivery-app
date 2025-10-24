package com.deliverysl.luxurydelivery.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateRiderDTO(
        @NotBlank(message = "Name cannot be empty")
        String name,
        @NotBlank(message = "Surname cannot be empty")
        String surname,
        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Invalid email format")
        String email,
        @NotBlank(message = "Password cannot be empty")
        @Size(min = 8,max = 16, message = "Password must have at least 8 characters and at most 16 characters")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_?*]).*$",
                message ="Password must contain at least one lowercase letter, one uppercase letter, and one special character")
        String password,
        @NotBlank(message = "ConfirmPassword cannot be empty")
        @Size(min = 8,max = 16, message = "ConfirmPassword must have at least 8 characters and at most 16 characters")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_?*]).*$",
                message ="ConfirmPassword must contain at least one lowercase letter, one uppercase letter, and one special character")
        String confirmPassword,
        @NotBlank(message = "Avatar url cannot be empty")
        @Pattern(
                message = ("Avatar must be .png,.jpg,.jpeg"),
                /* ^(https?://).+\\. */ //Dejo esto comentado porque no si lo añadiremos al patron
                //Ya que no se si cogeremos las imagenes de internet, se subiran a algun servidor, o solo sera local
                //Lo dejo basico y luego modificamos
                regexp = "^.+\\.(png|jpg|jpeg)$"
        )
        String avatar,
        @NotBlank(message = "DNI cannot be empty")
        @Size(min = 9,max = 9,message = "DNI must be exactly 9 characters long")
        @Pattern(
                message = "DNI must have 8 digits followed by a letter",
                regexp = "[0-9]{7,8}[A-Za-z]"
        )
        String dni,
        @NotBlank(message = "Location cannot be empty")
        String location
) {
}
