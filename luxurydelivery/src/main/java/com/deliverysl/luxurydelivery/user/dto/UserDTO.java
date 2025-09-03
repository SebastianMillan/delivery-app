package com.deliverysl.luxurydelivery.user.dto;

public record UserDTO(
        Long id,
        String name,
        String surnames,
        String email,
        String avatar,
        boolean activate
) {
}
