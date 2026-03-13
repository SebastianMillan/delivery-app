package com.deliverysl.luxurydelivery.user.controller;

import com.deliverysl.luxurydelivery.security.jwt.refresh.RefreshTokenRequest;
import com.deliverysl.luxurydelivery.security.jwt.refresh.RefreshTokenService;
import com.deliverysl.luxurydelivery.user.dto.CreateClientDTO;
import com.deliverysl.luxurydelivery.user.dto.JwtUserResponse;
import com.deliverysl.luxurydelivery.user.dto.LoginRequest;
import com.deliverysl.luxurydelivery.user.service.AuthenticationService;
import com.deliverysl.luxurydelivery.user.service.ClientService;
import com.deliverysl.luxurydelivery.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController implements AuthenticationControllerSwagger{

    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;
    private final ClientService clientService;

    @PostMapping("/login")
    @Override
    public ResponseEntity<JwtUserResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authenticationService.login(loginRequest));
    }

    @PostMapping("/client-registration")
    @Override
    public ResponseEntity<JwtUserResponse> createUserWithRoleClient(@RequestBody CreateClientDTO createClientDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clientService.register(createClientDTO));
    }

    @PostMapping("/refresh-token")
    @Override
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(refreshTokenService.refreshToken(refreshTokenRequest));
    }
}
