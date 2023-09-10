package com.SupermarketPOS.Backend.model.inventory_management;

import java.math.BigDecimal;
import java.util.List;

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
@Table(name = "purchaseItem")
public class PurchaseOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchaseId")
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;

    private Integer quantity;

    @Column(name = "purchaseItemCost", precision = 10, scale = 2)
    private BigDecimal purchaseItemCost;

    @Column(precision = 10, scale = 2)
    private BigDecimal amount;

    private Integer receivedQuantity; // when stock arrived based on the purchase order this recievedQuantity is updated

    @OneToMany(mappedBy = "purchaseOrderItem") // Referring to the field name in the StockArrival class
    private List<StockArrival> orderArrivals;
}
