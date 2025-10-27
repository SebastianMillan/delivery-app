package com.deliverysl.luxurydelivery.restaurant.exception;

public class ProtectedRestaurantException extends RuntimeException {
    public ProtectedRestaurantException(String message) {
        super(message);
    }
    public ProtectedRestaurantException(Long id){
        super("Restaurant with %d".formatted(id)+" is protected and cannot be delete(default restaurant)");
    }
}
