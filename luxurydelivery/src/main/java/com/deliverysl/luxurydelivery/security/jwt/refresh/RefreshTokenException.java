package com.deliverysl.luxurydelivery.security.jwt.refresh;

import com.deliverysl.luxurydelivery.security.jwt.access.JwtTokenException;

public class RefreshTokenException extends JwtTokenException {
    public RefreshTokenException(String message) {
        super(message);
    }
}
