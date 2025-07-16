package com.cardealership.CarDealership.Employee.Controller;

import com.cardealership.CarDealership.Employee.DTO.EmployeeRecordDTO;
import com.cardealership.CarDealership.Employee.Model.EmployeeModel;
import com.cardealership.CarDealership.Employee.Repository.EmployeeRepository;
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
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/employee")
    public ResponseEntity<EmployeeModel> saveEmployee(@RequestBody @Valid EmployeeRecordDTO employeeRecordDTO) {
        var employeeModel = new EmployeeModel();
        BeanUtils.copyProperties(employeeRecordDTO, employeeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeRepository.save(employeeModel));
    }

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeModel>> getAllEmployees() {
        List<EmployeeModel> employeeList = employeeRepository.findAll();
        if (!employeeList.isEmpty()) {
            for(EmployeeModel employee : employeeList) {
                UUID id = employee.getIdEmployee();
                employee.add(linkTo(methodOn(EmployeeController.class).getOneEmployee(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(employeeList);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Object> getOneEmployee(@PathVariable (value = "id") UUID id) {
        Optional<EmployeeModel> employee0 = employeeRepository.findById(id);
        if (employee0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Emplyee not found");
        }
        employee0.get().add(linkTo(methodOn(EmployeeController.class).getAllEmployees()).withRel("Employees list"));
        return ResponseEntity.status(HttpStatus.OK).body(employee0.get());
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable (value = "id") UUID id, @RequestBody @Valid EmployeeRecordDTO employeeRecordDTO) {
        Optional<EmployeeModel> employee0 = employeeRepository.findById(id);
        if (employee0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
        var employeeModel = employee0.get();
        BeanUtils.copyProperties(employeeRecordDTO, employeeModel);
        return ResponseEntity.status(HttpStatus.OK).body(employeeRepository.save(employeeModel));
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Object> deleteEmployee (@PathVariable (value = "id") UUID id) {
        Optional<EmployeeModel> employee0 = employeeRepository.findById(id);
        if (employee0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
        employeeRepository.delete(employee0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully");
    }
}
