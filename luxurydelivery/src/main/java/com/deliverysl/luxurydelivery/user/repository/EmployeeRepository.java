package com.deliverysl.luxurydelivery.user.repository;

import com.deliverysl.luxurydelivery.user.model.Employee;
import com.deliverysl.luxurydelivery.utils.BaseRepository;

import java.util.List;

public interface EmployeeRepository extends BaseRepository<Employee,Long> {

    List<Employee> findByRestaurant_id(Long restaurantId);
}
