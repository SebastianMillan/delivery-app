package com.deliverysl.luxurydelivery.user.service;

import com.deliverysl.luxurydelivery.restaurant.model.Restaurant;
import com.deliverysl.luxurydelivery.restaurant.service.RestaurantService;
import com.deliverysl.luxurydelivery.user.dto.CreateEmployeeDTO;
import com.deliverysl.luxurydelivery.user.exception.UserNotFoundException;
import com.deliverysl.luxurydelivery.user.mapper.EmployeeMapper;
import com.deliverysl.luxurydelivery.user.model.Employee;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService extends BaseServiceImpl<Employee,Long> {

    private final EmployeeMapper employeeMapper;
    private final RestaurantService restaurantService;

    @Transactional
    public Employee create(CreateEmployeeDTO createEmployeeDTO){

        if (!createEmployeeDTO.password().equals(createEmployeeDTO.confirmPassword())){
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        Restaurant restaurant = restaurantService.findByIdOrThrow(createEmployeeDTO.idRestaurant());
        Employee employee = employeeMapper.toEntity(createEmployeeDTO,restaurant);
        employee.setActivate(true);
        return save(employee);

    }

    @Transactional
    public Employee edit(CreateEmployeeDTO createEmployeeDTO,Long id){

        if (!createEmployeeDTO.password().equals(createEmployeeDTO.confirmPassword())){
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        Restaurant restaurant = restaurantService.findByIdOrThrow(createEmployeeDTO.idRestaurant());

        return findOptionalById(id).map(employee -> {
            employee.setName(createEmployeeDTO.name());
            employee.setSurnames(createEmployeeDTO.surname());
            employee.setEmail(createEmployeeDTO.email());
            employee.setPassword(createEmployeeDTO.confirmPassword());
            employee.setAvatar(createEmployeeDTO.avatar());
            employee.setRestaurant(restaurant);
            employee.setBoss(createEmployeeDTO.isBoss());
            return save(employee);
                }
        ).orElseThrow(() -> new UserNotFoundException(id));
    }
}
