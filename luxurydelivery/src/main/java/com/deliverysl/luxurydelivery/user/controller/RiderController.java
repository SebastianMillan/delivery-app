package com.deliverysl.luxurydelivery.user.controller;

import com.deliverysl.luxurydelivery.user.dto.ClientDTO;
import com.deliverysl.luxurydelivery.user.dto.CreateClientDTO;
import com.deliverysl.luxurydelivery.user.dto.CreateRiderDTO;
import com.deliverysl.luxurydelivery.user.dto.RiderDTO;
import com.deliverysl.luxurydelivery.user.mapper.RiderMapper;
import com.deliverysl.luxurydelivery.user.service.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rider")
public class RiderController
        implements RiderControllerSwagger{

    private final RiderService riderService;
    private final RiderMapper riderMapper;

    @PostMapping
    @Override
    public ResponseEntity<RiderDTO> create(@RequestBody CreateRiderDTO createRiderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(riderMapper.toDto(riderService.create(createRiderDTO)));
    }

    @PutMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<RiderDTO> edit(@RequestBody CreateRiderDTO createRiderDTO,@PathVariable Long id) {
        return ResponseEntity.ok(riderMapper.toDto(riderService.edit(createRiderDTO,id)));
    }
}
