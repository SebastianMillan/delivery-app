package com.deliverysl.luxurydelivery.user.repository;

import com.deliverysl.luxurydelivery.user.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
