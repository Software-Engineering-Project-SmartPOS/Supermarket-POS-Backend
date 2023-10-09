package com.SupermarketPOS.Backend.model.inventory_management;

import com.SupermarketPOS.Backend.model.common.Branch;

import jakarta.persistence.*;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StockLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "stockArrivalId")
    private StockArrival stockArrival;

//    public Integer getCategoryId() {
//        return item != null ? item.getCategory().getId() : null;
//    }

    @ManyToOne
    @JoinColumn(name = "branchId")
    private Branch branch;
    private LocalDate expiryDate;


//    @ManyToOne
//    @JoinColumn(name = "inventoryLocationId")
//    private Location inventoryLocation;
    private Float inventoryQuantity;


//    @ManyToOne
//    @JoinColumn(name = "stallLocationId")
//    private Location stallLocation;
    private Float stallQuantity;

//    private Double cost;
    private Float sellingPrice;
    private StockLevelStatus status;
    private Float discount;

    public StockLevel(Item item, Branch branch, LocalDate expiryDate, Float inventoryQuantity, Float sellingPrice, StockLevelStatus status, StockArrival stockArrival) {
        this.item = item;
        this.branch = branch;
        this.expiryDate = expiryDate;
        this.inventoryQuantity = inventoryQuantity;
        this.sellingPrice = sellingPrice;
        this.status = status;
        this.stallQuantity =0F;
        this.stockArrival = stockArrival;
    }
}
