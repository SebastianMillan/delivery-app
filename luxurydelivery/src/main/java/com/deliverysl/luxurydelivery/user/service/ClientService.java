package com.deliverysl.luxurydelivery.user.service;

import com.deliverysl.luxurydelivery.user.dto.CreateClientDTO;
import com.deliverysl.luxurydelivery.user.exception.UserNotFoundException;
import com.deliverysl.luxurydelivery.user.mapper.ClientMapper;
import com.deliverysl.luxurydelivery.user.model.Client;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class ClientService extends BaseServiceImpl<Client,Long> {

    private final ClientMapper clientMapper;

    @Transactional
    public Client create( CreateClientDTO createClientDTO){

        if (!createClientDTO.password().equals(createClientDTO.confirmPassword())){
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        Client client =  clientMapper.toEntity(createClientDTO);
        client.setActivate(true);
        return save(client);
    }

    @Transactional
    public Client edit( CreateClientDTO createClientDTO, Long id){


        if (!createClientDTO.password().equals(createClientDTO.confirmPassword())){
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        return findOptionalById(id).map(client -> {
            client.setName(createClientDTO.name());
            client.setSurnames(createClientDTO.surname());
            client.setEmail(createClientDTO.email());
            client.setPassword(createClientDTO.confirmPassword());
            client.setAvatar(createClientDTO.avatar());
            client.setAddress(createClientDTO.address());
            return save(client);
                }
        ).orElseThrow(() -> new UserNotFoundException(id));
    }
}
