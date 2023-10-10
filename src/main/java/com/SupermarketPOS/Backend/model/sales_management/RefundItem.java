package com.SupermarketPOS.Backend.model.sales_management;

import com.SupermarketPOS.Backend.model.inventory_management.Item;
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
public class RefundItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "refundId")
    private Refund refund;

    @ManyToOne
    @JoinColumn(name = "salesItemId")
    private SalesItem salesItem;

    private Float quantity;
    private Float unitPrice;

    // Getters and setters

    public RefundItem(Refund refund, SalesItem salesItem, Float quantity, Float unitPrice) {
        this.refund = refund;
        this.salesItem = salesItem;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}

