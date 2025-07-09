package com.cardealership.CarDealership.Buyer.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;
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

    @Transient
    public int getAge() {
        return LocalDate.now().getYear() - this.getDateOfBirth().getYear();
    }
}
