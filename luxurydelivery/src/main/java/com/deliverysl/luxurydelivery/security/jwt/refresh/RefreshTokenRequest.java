package com.deliverysl.luxurydelivery.security.jwt.refresh;

import jakarta.validation.constraints.NotEmpty;

public record RefreshTokenRequest(
        @NotEmpty String refreshToken
) {}
