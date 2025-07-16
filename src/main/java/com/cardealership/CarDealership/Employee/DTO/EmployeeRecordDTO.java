package com.cardealership.CarDealership.Employee.DTO;

import jakarta.validation.constraints.NotBlank;

public record EmployeeRecordDTO(@NotBlank String name) {
}
