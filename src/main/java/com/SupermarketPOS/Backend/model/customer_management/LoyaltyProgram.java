package com.SupermarketPOS.Backend.model.customer_management;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class LoyaltyProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String loyaltyProgramName;
    private String description;
    private Integer pointsThreshold;
    private Float discountPercentage;
    private Boolean status;

    public LoyaltyProgram(String loyaltyProgramName, String description, Integer pointsThreshold, Float discountPercentage) {
        this.loyaltyProgramName = loyaltyProgramName;
        this.description = description;
        this.pointsThreshold = pointsThreshold;
        this.discountPercentage = discountPercentage;
        this.status = true;
    }
}
