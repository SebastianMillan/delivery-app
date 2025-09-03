package com.deliverysl.luxurydelivery.user.mapper;

import com.deliverysl.luxurydelivery.user.dto.AdminDTO;
import com.deliverysl.luxurydelivery.user.dto.CreateAdminDTO;
import com.deliverysl.luxurydelivery.user.model.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminMapper {

    public AdminDTO toDto(Admin admin){
        return new AdminDTO(
                admin.getId(),
                admin.getName(),
                admin.getSurnames(),
                admin.getEmail(),
                admin.getAvatar(),
                admin.getPin(),
                admin.isActivate()
        );
    }

    public Admin toEntity(AdminDTO adminDTO){
        return Admin.builder()
                .id(adminDTO.id())
                .name(adminDTO.name())
                .surnames(adminDTO.surnames())
                .avatar(adminDTO.avatar())
                .pin(adminDTO.pin())
                .activate(adminDTO.activate())
                .build();
    }

    public CreateAdminDTO toCreateDto(Admin admin){
        return new CreateAdminDTO(
                admin.getName(),
                admin.getSurnames(),
                admin.getEmail(),
                admin.getPassword(),
                admin.getPassword(),
                admin.getAvatar(),
                admin.getPin()
        );
    }

    public Admin toEntity(CreateAdminDTO createAdminDTO){
        return Admin.builder()
                .name(createAdminDTO.name())
                .surnames(createAdminDTO.surname())
                .email(createAdminDTO.email())
                .password(createAdminDTO.password())
                .avatar(createAdminDTO.avatar())
                .pin(createAdminDTO.pin())
                .build();
    }
}
