package com.deliverysl.luxurydelivery.user.dto;

public record CreateEmployeeDTO(
        String name,
        String surname,
        String email,
        String password,
        String confirmPassword,
        String avatar,
        Long idRestaurant,
        boolean isBoss
) {
}
