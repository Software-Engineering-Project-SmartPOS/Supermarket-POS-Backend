package com.SupermarketPOS.Backend.model.inventory_management;

import lombok.Data;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

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
    private Integer id;

//    @ManyToOne
//    @JoinColumn(name = "itemId")
//    private Item item;

    @ManyToOne
    @JoinColumn(name = "purchaseItemId") // Name of the foreign key column
    private PurchaseOrderItem purchaseOrderItem; // Name of the field, it can have a different name

    private Float unitPrice;
    private Float quantity;
    private Float cost;
    private Boolean isTransferredToStore;
    private Integer branchId;

    @Column(name = "ArrivedAt", updatable = false, nullable = false)
    private Timestamp arrivedAt;

    @OneToMany(mappedBy = "stockArrival")
    private List<StockLevel> stockLevels;

    public StockArrival(PurchaseOrderItem purchaseOrderItem, Float quantity, Boolean isTransferredToStore, Timestamp arrivedAt) {
        this.purchaseOrderItem = purchaseOrderItem;
        this.quantity = quantity;
        this.isTransferredToStore = isTransferredToStore;
        this.arrivedAt = arrivedAt;
        this.branchId = purchaseOrderItem.getPurchaseOrder().getBranch().getId();
        this.isTransferredToStore = false;
    }


}
