package com.cardealership.CarDealership.Buyer.Repository;

import com.cardealership.CarDealership.Buyer.Model.BuyerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BuyerRepository extends JpaRepository<BuyerModel, UUID>{
}
