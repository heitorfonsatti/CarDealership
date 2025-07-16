package com.cardealership.CarDealership.Buyer.Model;


import com.cardealership.CarDealership.Car.Model.CarModel;
import com.cardealership.CarDealership.Employee.Model.EmployeeModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;
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

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<CarModel> cars;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeModel employee;

    @Transient
    public int getAge() {
        return LocalDate.now().getYear() - this.getDateOfBirth().getYear();
    }
}
