package com.cardealership.CarDealership.Car.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CarRecordDTO(@NotBlank String brand, @NotBlank String model, @NotNull Integer year, @NotBlank String usage, @NotBlank String accessories, @NotNull BigDecimal value, @NotNull UUID buyerId) {
}
