package com.cardealership.CarDealership.Buyer.Model;


import com.cardealership.CarDealership.Car.Model.CarModel;
import com.cardealership.CarDealership.Employee.Model.EmployeeModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_buyer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyerModel extends RepresentationModel<BuyerModel> implements Serializable {

    private static final long serialVersionUID = 1L;

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idBuyer;
    private String name;
    private String location;
    private String email;
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private EmployeeModel employee;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference  // Controle de serialização, resolve o loop
    private List<CarModel> cars = new ArrayList<>();

    @Transient
    public int getAge() {
        return LocalDate.now().getYear() - this.getDateOfBirth().getYear();
    }
}
