package com.cardealership.CarDealership.Car.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CarRecordDTO(@NotBlank String brand, @NotBlank String model, @NotNull BigDecimal value) {
}
