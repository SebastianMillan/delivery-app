package com.deliverysl.luxurydelivery.category.exception;

public class ProtectedCategoryException extends RuntimeException {
    public ProtectedCategoryException(String message) {
        super(message);
    }
    public ProtectedCategoryException(Long id){
        super("Category with %d".formatted(id)+" is protected and cannot be delete(default category)");
    }
}
