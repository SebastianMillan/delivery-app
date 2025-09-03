package com.deliverysl.luxurydelivery.user.repository;

import com.deliverysl.luxurydelivery.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User>findByActivateTrue();
    List<User>findByActivateFalse();
}
