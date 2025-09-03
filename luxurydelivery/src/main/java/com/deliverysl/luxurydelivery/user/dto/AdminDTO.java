package com.deliverysl.luxurydelivery.user.dto;

public record AdminDTO(
        Long id,
        String name,
        String surnames,
        String email,
        String avatar,
        String pin,
        boolean activate
) {
}
