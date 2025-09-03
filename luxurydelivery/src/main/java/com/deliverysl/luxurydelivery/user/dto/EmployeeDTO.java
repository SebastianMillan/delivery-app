package com.deliverysl.luxurydelivery.user.dto;

public record EmployeeDTO(
        Long id,
        String name,
        String surname,
        String dni,
        String restaurantName,
        boolean isBoss,
        int orderlistNumber,
        boolean activate
) {
}
