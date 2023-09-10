package com.SupermarketPOS.Backend.model.sales_management;

import com.SupermarketPOS.Backend.model.inventory_management.StockLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SalesItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = Sale.class
    )
    @JoinColumn(name = "salesId")
    private Sale sales;

    @ManyToOne
    @JoinColumn(name = "stockLevelId")
    private StockLevel stockLevel;

    private Integer quantity;
    private Double unitPrice;

    // Getters and setters
}
