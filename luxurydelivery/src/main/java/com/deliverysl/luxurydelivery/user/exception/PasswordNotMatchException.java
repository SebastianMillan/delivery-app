package com.deliverysl.luxurydelivery.user.exception;

public class PasswordNotMatchException extends RuntimeException {
    public PasswordNotMatchException(String message) {
        super(message);
    }

    public PasswordNotMatchException(){
        super("Password and Confirm do not match");
    }
}
