package com.SupermarketPOS.Backend.model.inventory_management;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class PurchaseOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "purchaseOrderId")
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "itemSupplyId")
    private ItemSupply itemSupply;

    private Float quantity;

    private Float purchaseItemUnitCost;

    private Float totalCost;

    private Float receivedQuantity; // when stock arrived based on the purchase order this recievedQuantity is updated

    @OneToMany(mappedBy = "purchaseOrderItem") // Referring to the field name in the StockArrival class
    private List<StockArrival> orderArrivals;

    private PurchaseOrderStatus purchaseOrderItemStatus;

    public PurchaseOrderItem(PurchaseOrder purchaseOrder, Item item, ItemSupply itemSupply, Float quantity, Float purchaseItemUnitCost) {
        this.purchaseOrder = purchaseOrder;
        this.item = item;
        this.itemSupply = itemSupply;
        this.quantity = quantity;
        this.purchaseItemUnitCost = purchaseItemUnitCost;
        this.receivedQuantity = 0F;
        this.totalCost = 0F;
    }
}
