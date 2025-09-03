package com.deliverysl.luxurydelivery.user.repository;

import com.deliverysl.luxurydelivery.user.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
