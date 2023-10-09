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
    @JoinColumn(name = "itemId")
    private Item item;

    private Integer quantity;
    private Double unitPrice;

    // Getters and setters
}

