package com.deliverysl.luxurydelivery.user.controller;

import com.deliverysl.luxurydelivery.user.dto.AdminDTO;
import com.deliverysl.luxurydelivery.user.dto.CreateAdminDTO;
import com.deliverysl.luxurydelivery.user.mapper.AdminMapper;
import com.deliverysl.luxurydelivery.user.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController
        implements AdminControllerSwagger{

    private final AdminService adminService;
    private final AdminMapper adminMapper;

    @PostMapping
    @Override
    public ResponseEntity<AdminDTO> create(@RequestBody CreateAdminDTO createAdminDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminMapper.toDto(adminService.create(createAdminDTO)));
    }

    @PutMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<AdminDTO> edit(@RequestBody CreateAdminDTO createAdminDTO,@PathVariable Long id) {
        return ResponseEntity.ok(adminMapper.toDto(adminService.edit(createAdminDTO,id)));
    }
}
