package com.SupermarketPOS.Backend.model.inventory_management;

import lombok.Data;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.engine.transaction.internal.TransactionImpl;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StockArrival {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "purchaseItemId") // Name of the foreign key column
    private PurchaseOrderItem purchaseOrderItem; // Name of the field, it can have a different name

    private Integer quantity;
    private Double cost;
    private Double sellingPrice;


    private Timestamp expiryDate;

    @Column(name = "ArrivedAt", updatable = false, nullable = false)
    private Timestamp arrivedAt;
}
