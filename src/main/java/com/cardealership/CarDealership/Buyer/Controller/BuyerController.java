package com.cardealership.CarDealership.Buyer.Controller;

import com.cardealership.CarDealership.Buyer.DTO.BuyerRecordDTO;
import com.cardealership.CarDealership.Buyer.Model.BuyerModel;
import com.cardealership.CarDealership.Buyer.Repository.BuyerRepository;
import com.cardealership.CarDealership.Employee.Model.EmployeeModel;
import com.cardealership.CarDealership.Employee.Repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
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
public class BuyerController {

    @Autowired
    BuyerRepository buyerRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/buyer")
    public ResponseEntity<Object> saveBuyer(@RequestBody @Valid BuyerRecordDTO buyerRecordDTO) {
        Optional<EmployeeModel> employee0 = employeeRepository.findById(buyerRecordDTO.employeeId());
        if (employee0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }

        var buyerModel = new BuyerModel();
        BeanUtils.copyProperties(buyerRecordDTO, buyerModel);
        buyerModel.setEmployee(employee0.get()); // associar o funcionário

        return ResponseEntity.status(HttpStatus.CREATED).body(buyerRepository.save(buyerModel));
    }

    //Get all
    @GetMapping("/buyer")
    public ResponseEntity<List<BuyerModel>> getAllBuyers() {
        List<BuyerModel> buyerList = buyerRepository.findAll();
        if (!buyerList.isEmpty()) {
            for (BuyerModel buyer : buyerList) {
                UUID id = buyer.getIdBuyer();
                buyer.add(linkTo(methodOn(BuyerController.class).getOneBuyer(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(buyerList);
    }

    //Get one
    @GetMapping("/buyer/{id}")
    public ResponseEntity<Object> getOneBuyer(@PathVariable(value="id") UUID id) {
        Optional<BuyerModel> buyer0 = buyerRepository.findById(id);
        if (buyer0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Buyer not found");
        }
        buyer0.get().add(linkTo(methodOn(BuyerController.class).getAllBuyers()).withRel("Buyer List"));
        return ResponseEntity.status(HttpStatus.OK).body(buyer0.get());
    }

    //Update
    @PutMapping("/buyer/{id}")
    public ResponseEntity<Object> updateBuyer(@PathVariable(value="id") UUID id, @RequestBody @Valid BuyerRecordDTO buyerRecordDTO) {
        Optional<BuyerModel> buyer0 = buyerRepository.findById(id);
        if (buyer0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Buyer not found");
        }
        var buyerModel = buyer0.get();
        BeanUtils.copyProperties(buyerRecordDTO, buyerModel);
        return ResponseEntity.status(HttpStatus.OK).body(buyerRepository.save(buyerModel));
    }

    //Delete
    @DeleteMapping("/buyer/{id}")
    public ResponseEntity<Object> deleteBuyer(@PathVariable(value="id") UUID id) {
        Optional<BuyerModel> buyer0 = buyerRepository.findById(id);
        if (buyer0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Buyer not found");
        }
        buyerRepository.delete(buyer0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Buyer Deleted Successfully");
    }
}
