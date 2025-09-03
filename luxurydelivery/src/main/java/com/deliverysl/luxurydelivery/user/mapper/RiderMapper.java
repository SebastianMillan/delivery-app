package com.deliverysl.luxurydelivery.user.mapper;

import com.deliverysl.luxurydelivery.user.dto.CreateRiderDTO;
import com.deliverysl.luxurydelivery.user.dto.RiderDTO;
import com.deliverysl.luxurydelivery.user.model.Rider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RiderMapper {

    public RiderDTO toDto(Rider rider){
        return new RiderDTO(
                rider.getId(),
                rider.getName(),
                rider.getSurnames(),
                rider.getEmail(),
                rider.getDni(),
                rider.getLocation(),
                rider.isActivate()
        );
    }

    public Rider toEntity(RiderDTO riderDTO){
        return Rider.builder()
                .id(riderDTO.id())
                .name(riderDTO.name())
                .surnames(riderDTO.surname())
                .email(riderDTO.email())
                .dni(riderDTO.dni())
                .location(riderDTO.location())
                .activate(riderDTO.activate())
                .build();
    }

    public CreateRiderDTO toCreateDto(Rider rider){
        return new CreateRiderDTO(
                rider.getName(),
                rider.getSurnames(),
                rider.getEmail(),
                rider.getPassword(),
                rider.getPassword(),
                rider.getAvatar(),
                rider.getDni(),
                rider.getLocation()
        );
    }

    public Rider toEntity(CreateRiderDTO createRiderDTO){
        return Rider.builder()
                .name(createRiderDTO.name())
                .surnames(createRiderDTO.surname())
                .email(createRiderDTO.email())
                .password(createRiderDTO.confirmPassword())
                .avatar(createRiderDTO.avatar())
                .dni(createRiderDTO.dni())
                .location(createRiderDTO.location())
                .build();
    }
}
