package com.deliverysl.luxurydelivery.user.dto;

public record CreateAdminDTO(
        String name,
        String surname,
        String email,
        String password,
        String confirmPassword,
        String avatar,
        String pin
) {
}
