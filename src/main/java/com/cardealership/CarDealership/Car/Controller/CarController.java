package com.cardealership.CarDealership.Car.Controller;

import com.cardealership.CarDealership.Car.DTO.CarRecordDTO;
import com.cardealership.CarDealership.Car.Model.CarModel;
import com.cardealership.CarDealership.Car.Repository.CarRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CarController {

    @Autowired
    CarRepository carRepository;

    @PostMapping("/cars")
    public ResponseEntity<CarModel> saveCar(@RequestBody @Valid CarRecordDTO carRecordDTO) {
        var carModel = new CarModel();
        BeanUtils.copyProperties(carRecordDTO, carModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(carRepository.save(carModel));
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarModel>> getAllCars() {
        List<CarModel> carsList = carRepository.findAll();
        if (!carsList.isEmpty()) {
            for (CarModel car : carsList) {
                UUID id = car.getIdCar();
                car.add(linkTo(methodOn(CarController.class).getOneCar(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(carsList);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Object> getOneCar(@PathVariable(value = "id") UUID id) {
        Optional<CarModel> car0 = carRepository.findById(id);
        if (car0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found");
        }
        car0.get().add(linkTo(methodOn(CarController.class).getAllCars()).withRel("Cars list"));
        return ResponseEntity.status(HttpStatus.OK).body(car0.get());
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable(value = "id") UUID id, @RequestBody @Valid CarRecordDTO carRecordDTO) {
        Optional<CarModel> car0 = carRepository.findById(id);
        if (car0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found");
        }
        var carModel = car0.get();
        BeanUtils.copyProperties(carRecordDTO, carModel);
        return ResponseEntity.status(HttpStatus.OK).body(carRepository.save(carModel));
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable(value = "id") UUID id) {
        Optional<CarModel> car0 = carRepository.findById(id);
        if (car0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found");
        }
        carRepository.delete(car0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Car deleted successfully");
    }

}
