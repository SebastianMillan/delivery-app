package com.deliverysl.luxurydelivery.user.service;

import com.deliverysl.luxurydelivery.user.exception.UserNotFoundException;
import com.deliverysl.luxurydelivery.user.model.User;
import com.deliverysl.luxurydelivery.user.repository.UserRepository;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService extends BaseServiceImpl<User,Long> {

    private final UserRepository userRepository;

    public User findByIdOrThrow(Long id){
        return findOptionalById(id)
                .orElseThrow(() ->new UserNotFoundException(id));
    }

    public User findByUsernameOrThrow(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

}
