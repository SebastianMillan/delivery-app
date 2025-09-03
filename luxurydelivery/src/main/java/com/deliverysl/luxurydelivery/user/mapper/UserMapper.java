package com.deliverysl.luxurydelivery.user.mapper;

import com.deliverysl.luxurydelivery.user.dto.UserDTO;
import com.deliverysl.luxurydelivery.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
                user.isActivate()
        );
    }
}
