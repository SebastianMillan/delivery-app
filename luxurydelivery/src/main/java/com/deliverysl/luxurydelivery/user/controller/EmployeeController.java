package com.deliverysl.luxurydelivery.user.controller;

import com.deliverysl.luxurydelivery.user.dto.CreateEmployeeDTO;
import com.deliverysl.luxurydelivery.user.dto.EmployeeDTO;
import com.deliverysl.luxurydelivery.user.mapper.EmployeeMapper;
import com.deliverysl.luxurydelivery.user.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController implements EmployeeControllerSwagger{

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @PostMapping
    @Override
    public ResponseEntity<EmployeeDTO> create(@RequestBody CreateEmployeeDTO createEmployeeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeMapper.toDto(employeeService.create(createEmployeeDTO)));
    }

    @PutMapping("/{id:[0-9]+}")
    @Override
    public ResponseEntity<EmployeeDTO> edit(@RequestBody CreateEmployeeDTO createEmployeeDTO, @PathVariable Long id) {
        return ResponseEntity.ok(employeeMapper.toDto(employeeService.edit(createEmployeeDTO,id)));
    }
}
