package com.cardealership.CarDealership.Employee.Repository;

import com.cardealership.CarDealership.Employee.Model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, UUID> {
}
