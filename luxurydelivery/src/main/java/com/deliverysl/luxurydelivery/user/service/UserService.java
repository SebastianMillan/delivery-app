package com.deliverysl.luxurydelivery.user.service;

import com.deliverysl.luxurydelivery.user.exception.UserNotFoundException;
import com.deliverysl.luxurydelivery.user.model.User;
import com.deliverysl.luxurydelivery.user.repository.UserRepository;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService extends BaseServiceImpl<User,Long> {

    private final UserRepository userRepository;

    public User findByIdOrThrow(Long id){
        return findOptionalById(id)
                .orElseThrow(() ->new UserNotFoundException(id));
    }

    @Transactional
    public void deleteUserById(Long id){
        User user = findByIdOrThrow(id);

        user.setActivate(false);

        save(user);
    }

    @Transactional
    public User activateUser(Long id){
        User user = findByIdOrThrow(id);

        if (!user.isActivate()){
            user.setActivate(true);
            save(user);
        }
        return user;
    }

    public List<User> findByActivateTrue(){
        return userRepository.findByActivateTrue();
    }

    public List<User> findByActivateFalse(){
        return userRepository.findByActivateFalse();
    }

}
