package com.cardealership.CarDealership.Car.Repository;

import com.cardealership.CarDealership.Car.Model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<CarModel, UUID> {
}
