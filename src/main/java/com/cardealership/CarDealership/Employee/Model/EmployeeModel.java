package com.cardealership.CarDealership.Employee.Model;

import com.cardealership.CarDealership.Buyer.Model.BuyerModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel extends RepresentationModel<EmployeeModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idEmployee;
    private String name;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<BuyerModel> buyers;

}
