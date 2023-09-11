package com.SupermarketPOS.Backend.repository.customer_management;

import com.SupermarketPOS.Backend.model.customer_management.LoyaltyProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyProgramRepository extends JpaRepository<LoyaltyProgram,Integer> {

}
