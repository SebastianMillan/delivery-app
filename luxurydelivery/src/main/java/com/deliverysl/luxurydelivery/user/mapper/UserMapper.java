package com.deliverysl.luxurydelivery.user.mapper;

import com.deliverysl.luxurydelivery.user.dto.JwtUserResponse;
import com.deliverysl.luxurydelivery.user.dto.UserDTO;
import com.deliverysl.luxurydelivery.user.dto.UserResponse;
import com.deliverysl.luxurydelivery.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserDTO toDto(User user){
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getSurnames(),
                user.getEmail(),
                user.getAvatar(),
                user.isActive()
        );
    }

    public UserResponse toResponseDto(User user){
        return UserResponse.builder()
                .id(user.getId().toString())
                .username(user.getUsername())
                .avatar(user.getAvatar())
                .fullName(user.getName() + " " + user.getSurnames())
                .roles(user.getRoles().stream()
                        .map(Enum::name)
                        .collect(Collectors.toSet()))
                .createdAt(user.getCreatedAt())
                .build();
    }

    public JwtUserResponse toJwtResponseDto(User user, String token, String refreshToken){
        JwtUserResponse result = new JwtUserResponse(toResponseDto(user));
        result.setToken(token);
        result.setRefreshToken(refreshToken);
        return result;
    }
}
