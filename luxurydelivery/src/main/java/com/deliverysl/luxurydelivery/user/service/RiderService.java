package com.deliverysl.luxurydelivery.user.service;

import com.deliverysl.luxurydelivery.user.dto.CreateRiderDTO;
import com.deliverysl.luxurydelivery.user.exception.UserNotFoundException;
import com.deliverysl.luxurydelivery.user.mapper.RiderMapper;
import com.deliverysl.luxurydelivery.user.model.Rider;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RiderService extends BaseServiceImpl<Rider,Long> {

    private final RiderMapper riderMapper;

    @Transactional
    public Rider create(CreateRiderDTO createRiderDTO){

        if (!createRiderDTO.password().equals(createRiderDTO.confirmPassword())){
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        Rider rider = riderMapper.toEntity(createRiderDTO);
        rider.setActivate(true);
        return save(rider);

    }

    @Transactional
    public Rider edit(CreateRiderDTO createRiderDTO, Long id){

        if (!createRiderDTO.password().equals(createRiderDTO.confirmPassword())){
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        return findOptionalById(id).map(rider -> {
            rider.setName(createRiderDTO.name());
            rider.setSurnames(createRiderDTO.surname());
            rider.setEmail(createRiderDTO.email());
            rider.setPassword(createRiderDTO.confirmPassword());
            rider.setAvatar(createRiderDTO.avatar());
            rider.setDni(createRiderDTO.dni());
            rider.setLocation(createRiderDTO.location());
            return save(rider);
                }
        ).orElseThrow(() -> new UserNotFoundException(id));
    }

}
