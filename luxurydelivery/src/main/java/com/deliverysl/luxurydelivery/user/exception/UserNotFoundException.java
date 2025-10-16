package com.deliverysl.luxurydelivery.user.exception;

import jakarta.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super("Products not found");
    }

    public UserNotFoundException(Long id){
        super("User not found by id %d".formatted(id));
    }


}
