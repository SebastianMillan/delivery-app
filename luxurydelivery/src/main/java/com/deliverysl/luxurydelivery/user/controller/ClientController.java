package com.deliverysl.luxurydelivery.user.controller;

import com.deliverysl.luxurydelivery.user.dto.ClientDTO;
import com.deliverysl.luxurydelivery.user.dto.CreateClientDTO;
import com.deliverysl.luxurydelivery.user.mapper.ClientMapper;
import com.deliverysl.luxurydelivery.user.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController
        implements ClientControllerSwagger{

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @PostMapping
    @Override
    public ResponseEntity<ClientDTO> create(@RequestBody CreateClientDTO createClientDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientMapper.toDto(clientService.create(createClientDTO))) ;
    }

    @PutMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<ClientDTO> edit(@RequestBody CreateClientDTO createClientDTO,@PathVariable Long id) {
        return ResponseEntity.ok(clientMapper.toDto(clientService.edit(createClientDTO,id)));
    }
}
