package com.SupermarketPOS.Backend.model.inventory_management;

import com.SupermarketPOS.Backend.model.common.Branch;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "branchId")
    private Branch branch;

    private LocalDate expiryDate;


    @ManyToOne
    @JoinColumn(name = "inventoryLocationId")
    private Location inventoryLocation;
    private Integer inventoryQuantity;


    @ManyToOne
    @JoinColumn(name = "stallLocationId")
    private Location stallLocation;
    private Integer stallQuantity;

//    private Double cost;
    private Float sellingPrice;


}
