package com.deliverysl.luxurydelivery.restaurant.exception;

import jakarta.persistence.EntityNotFoundException;

public class RestaurantNotFoundException extends EntityNotFoundException {

    public RestaurantNotFoundException (){super("Restaurants not found");}

    public RestaurantNotFoundException(Long id){super("Restaurant not found id %d".formatted(id));}
}
