package com.deliverysl.luxurydelivery.user.controller;

import com.deliverysl.luxurydelivery.security.jwt.refresh.RefreshTokenRequest;
import com.deliverysl.luxurydelivery.user.dto.CreateClientDTO;
import com.deliverysl.luxurydelivery.user.dto.JwtUserResponse;
import com.deliverysl.luxurydelivery.user.dto.LoginRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Authentication", description = "Operaciones CRUD sobre autenticación")
public interface AuthenticationControllerSwagger {

    ResponseEntity<JwtUserResponse> login(LoginRequest loginRequest);

    ResponseEntity<JwtUserResponse> createUserWithRoleClient(CreateClientDTO createClientDTO);

    ResponseEntity<?> refreshToken(RefreshTokenRequest refreshTokenRequest);

}

