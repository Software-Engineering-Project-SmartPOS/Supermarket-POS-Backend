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
    private Integer     id;

    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = Sale.class
    )
    @JoinColumn(name = "salesId")
    private Sale sales;

    @ManyToOne
    @JoinColumn(name = "stockLevelId")
    private StockLevel stockLevel;

    private Float quantity;
    private Float unitPrice;
    private Float subtotal;
    private Float discountGiven;
    private Float total;



    // Getters and setters

    public SalesItem(Sale sales, StockLevel stockLevel, Float quantity, Float unitPrice, Float discountGiven) {
        this.sales = sales;
        this.stockLevel = stockLevel;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = quantity*unitPrice;
        this.discountGiven = discountGiven;
        this.total = this.subtotal*(1-discountGiven);
    }
}
