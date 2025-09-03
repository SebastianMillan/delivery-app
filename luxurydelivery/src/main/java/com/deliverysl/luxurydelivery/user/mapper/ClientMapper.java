package com.deliverysl.luxurydelivery.user.mapper;

import com.deliverysl.luxurydelivery.user.dto.ClientDTO;
import com.deliverysl.luxurydelivery.user.dto.CreateClientDTO;
import com.deliverysl.luxurydelivery.user.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientMapper {

    public ClientDTO toDto(Client client){
        return new ClientDTO(
                client.getId(),
                client.getName(),
                client.getSurnames(),
                client.getEmail(),
                client.getAddress(),
                client.isActivate()
        );
    }

    public Client toEntity(ClientDTO clientDTO){
        return Client.builder()
                .id(clientDTO.id())
                .name(clientDTO.name())
                .surnames(clientDTO.surnames())
                .email(clientDTO.email())
                .address(clientDTO.adress())
                .activate(clientDTO.active())
                .build();
    }

    public CreateClientDTO toCreateDto(Client client){
        return new CreateClientDTO(
                client.getName(),
                client.getSurnames(),
                client.getEmail(),
                client.getPassword(),
                client.getPassword(),
                client.getAvatar(),
                client.getAddress()
        );
    }

    public Client toEntity(CreateClientDTO createClientDTO){
        return Client.builder()
                .name(createClientDTO.name())
                .surnames(createClientDTO.surname())
                .email(createClientDTO.email())
                .password(createClientDTO.confirmPassword())
                .avatar(createClientDTO.avatar())
                .address(createClientDTO.address())
                .build();
    }
}
