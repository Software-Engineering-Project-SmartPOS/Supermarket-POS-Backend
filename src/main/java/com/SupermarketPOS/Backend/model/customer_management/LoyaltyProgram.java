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
    private String loyalty_program_name;
    private String description;
    private Integer points_threshold;
    private Double discount_percentage;

    public LoyaltyProgram(String loyalty_program_name, String description, Integer points_threshold, Double discount_percentage) {
        this.loyalty_program_name = loyalty_program_name;
        this.description = description;
        this.points_threshold = points_threshold;
        this.discount_percentage = discount_percentage;
    }
}
