package com.deliverysl.luxurydelivery.user.dto;

public record ClientDTO(
        Long id,
        String name,
        String surnames,
        String email,
        String adress,
        boolean active
) {
}
