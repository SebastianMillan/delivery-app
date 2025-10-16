package com.deliverysl.luxurydelivery.user.dto;

public record CreateRiderDTO(
        String name,
        String surname,
        String email,
        String password,
        String confirmPassword,
        String avatar,
        String dni,
        String location
) {
}
