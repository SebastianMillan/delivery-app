package com.deliverysl.luxurydelivery.user.repository;

import com.deliverysl.luxurydelivery.user.model.User;
import com.deliverysl.luxurydelivery.utils.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User,Long> {

    Optional<User> findByUsername(String username);

}
