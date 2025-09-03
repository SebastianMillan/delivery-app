package com.deliverysl.luxurydelivery.user.dto;

public record RiderDTO(
        Long id,
        String name,
        String surname,
        String email,
        String dni,
        String location,
        boolean activate
) {
}
