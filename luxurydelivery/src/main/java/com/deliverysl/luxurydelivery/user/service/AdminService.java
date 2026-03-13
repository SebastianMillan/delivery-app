package com.deliverysl.luxurydelivery.user.service;

import com.deliverysl.luxurydelivery.user.dto.CreateAdminDTO;
import com.deliverysl.luxurydelivery.user.exception.PasswordNotMatchException;
import com.deliverysl.luxurydelivery.user.exception.UserNotFoundException;
import com.deliverysl.luxurydelivery.user.mapper.AdminMapper;
import com.deliverysl.luxurydelivery.user.model.Admin;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AdminService extends BaseServiceImpl<Admin,Long> {

    private final AdminMapper adminMapper;

    @Transactional
    public Admin create(@RequestBody CreateAdminDTO createAdminDTO){

        if (!createAdminDTO.password().equals(createAdminDTO.confirmPassword())){
            throw new PasswordNotMatchException();
        }

        Admin admin = adminMapper.toEntity(createAdminDTO);
        return save(admin);
    }

    @Transactional
    public Admin edit(@RequestBody CreateAdminDTO createAdminDTO, @PathVariable Long id){

        if (!createAdminDTO.password().equals(createAdminDTO.confirmPassword())){
            throw new PasswordNotMatchException();
        }

        return findOptionalById(id).map(ad ->{
            ad.setName(createAdminDTO.name());
            ad.setSurnames(createAdminDTO.surname());
            ad.setPassword(createAdminDTO.password());
            ad.setAvatar(createAdminDTO.avatar());
            ad.setPin(createAdminDTO.pin());
            return save(ad);
        }).orElseThrow(() -> new UserNotFoundException(id));

    }
}
