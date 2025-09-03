package com.deliverysl.luxurydelivery.user.mapper;

import com.deliverysl.luxurydelivery.order.mapper.OrderMapper;
import com.deliverysl.luxurydelivery.restaurant.model.Restaurant;
import com.deliverysl.luxurydelivery.user.dto.CreateEmployeeDTO;
import com.deliverysl.luxurydelivery.user.dto.EmployeeDTO;
import com.deliverysl.luxurydelivery.user.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {

    private final OrderMapper orderMapper;

    public EmployeeDTO toDto(Employee employee){
        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getSurnames(),
                employee.getDni(),
                employee.getRestaurant().getName(),
                employee.isBoss(),
                employee.getOrderList().size(),
                employee.isActivate()
        );
    }

    public Employee toEntity(EmployeeDTO employeeDTO, Restaurant restaurant){
        return Employee.builder()
                .id(employeeDTO.id())
                .name(employeeDTO.name())
                .surnames(employeeDTO.surname())
                .dni(employeeDTO.dni())
                .restaurant(restaurant)
                .isBoss(employeeDTO.isBoss())
                .orderList(new ArrayList<>())
                .activate(employeeDTO.activate())
                .build();
    }

    public CreateEmployeeDTO toCreateDto(Employee employee){
        return new CreateEmployeeDTO(
                employee.getName(),
                employee.getSurnames(),
                employee.getEmail(),
                employee.getPassword(),
                employee.getPassword(),
                employee.getAvatar(),
                employee.getRestaurant().getId(),
                employee.isBoss()
        );
    }

    public Employee toEntity(CreateEmployeeDTO createEmployeeDTO,Restaurant restaurant){
        return Employee.builder()
                .name(createEmployeeDTO.name())
                .surnames(createEmployeeDTO.surname())
                .email(createEmployeeDTO.email())
                .password(createEmployeeDTO.confirmPassword())
                .avatar(createEmployeeDTO.avatar())
                .restaurant(restaurant)
                .isBoss(createEmployeeDTO.isBoss())
                .orderList(new ArrayList<>())
                .build();
    }

}
