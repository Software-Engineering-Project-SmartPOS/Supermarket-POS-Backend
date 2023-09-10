package com.SupermarketPOS.Backend.model.inventory_management;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stockArrangement")
public class StockArrangement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stockLevelId")
    private StockLevel stockLevel;

    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location;

    private Integer quantity;

    // Constructors, getters, and setters
}

