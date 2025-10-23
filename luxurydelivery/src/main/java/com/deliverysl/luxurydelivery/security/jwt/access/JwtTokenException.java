package com.deliverysl.luxurydelivery.security.jwt.access;

public class JwtTokenException extends RuntimeException {
    public JwtTokenException(String message) {
        super(message);
    }
}
