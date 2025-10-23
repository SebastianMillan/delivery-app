package com.deliverysl.luxurydelivery.user.service;

import com.deliverysl.luxurydelivery.security.jwt.access.JwtProvider;
import com.deliverysl.luxurydelivery.security.jwt.refresh.RefreshTokenRequest;
import com.deliverysl.luxurydelivery.security.jwt.refresh.RefreshTokenService;
import com.deliverysl.luxurydelivery.user.dto.CreateClientDTO;
import com.deliverysl.luxurydelivery.user.dto.JwtUserResponse;
import com.deliverysl.luxurydelivery.user.exception.UserNotFoundException;
import com.deliverysl.luxurydelivery.user.mapper.ClientMapper;
import com.deliverysl.luxurydelivery.user.model.Client;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService extends BaseServiceImpl<Client,Long> {

    private final ClientMapper clientMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    @Transactional
    public Client create( CreateClientDTO createClientDTO){
        if (!createClientDTO.password().equals(createClientDTO.confirmPassword())){
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        Client client =  clientMapper.toEntity(createClientDTO);
        client.setPassword(passwordEncoder.encode(createClientDTO.password()));
        return save(client);
    }

    public JwtUserResponse register(CreateClientDTO createClientDTO){
        Client createdClient = create(createClientDTO);
        String token = jwtProvider.generateToken(createdClient);
        refreshTokenService.deleteByUser(createdClient);
        return refreshTokenService.createRefreshAndResponse(createdClient);
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
