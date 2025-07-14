package com.deliverysl.luxurydelivery.category.exception;

import jakarta.persistence.EntityNotFoundException;

public class CategoryNotFoundException extends EntityNotFoundException {

    public CategoryNotFoundException() {
        super("Categories not found");
    }

    public CategoryNotFoundException(Long id){
        super("Category not found id %d".formatted(id));
    }

}
