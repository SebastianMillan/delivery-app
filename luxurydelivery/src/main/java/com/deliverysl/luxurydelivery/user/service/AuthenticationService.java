package com.deliverysl.luxurydelivery.user.service;

import com.deliverysl.luxurydelivery.security.jwt.access.JwtProvider;
import com.deliverysl.luxurydelivery.security.jwt.refresh.RefreshTokenService;
import com.deliverysl.luxurydelivery.user.dto.JwtUserResponse;
import com.deliverysl.luxurydelivery.user.dto.LoginRequest;
import com.deliverysl.luxurydelivery.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authManager;
    private final RefreshTokenService refreshTokenService;

    public JwtUserResponse login(LoginRequest loginRequest){
        Authentication authentication =
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.username(),
                                loginRequest.password()
                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        refreshTokenService.deleteByUser(user);
        return refreshTokenService.createRefreshAndResponse(user);
    }
}
