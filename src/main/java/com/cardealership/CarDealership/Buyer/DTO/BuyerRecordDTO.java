package com.cardealership.CarDealership.Buyer.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record BuyerRecordDTO(@NotBlank String name, @NotBlank String location, @NotNull LocalDate dateOfBirth, @NotNull UUID employeeId) {
}
