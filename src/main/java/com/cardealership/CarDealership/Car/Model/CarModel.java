package com.cardealership.CarDealership.Car.Model;

import com.cardealership.CarDealership.Buyer.Model.BuyerModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_car")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarModel extends RepresentationModel<CarModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCar;
    private String brand;
    private String model;
    private int year;
    private String usage;
    private String accessories;
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    @JsonBackReference  // Complementa o @JsonManagedReference do Buyer
    private BuyerModel buyer;
}
