package com.SupermarketPOS.Backend.model.inventory_management;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "damage_stock")
public class DamageStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stockLevelId")
    private StockLevel stockLevel;

    private Integer quantity;

//    @Column(name = "createdAt")
    private Timestamp createdAt;

    private String reason;

    // Constructors, getters, and setters
}
