package com.deliverysl.luxurydelivery.type.exception;

public class ProtectedTypeException extends RuntimeException {
    public ProtectedTypeException(String message) {
        super(message);
    }
    public ProtectedTypeException(Long id){
        super("Type with %d".formatted(id)+" is protected and cannot be delete(default type)");
    }
}
