package com.SupermarketPOS.Backend.model.inventory_management;

import com.SupermarketPOS.Backend.model.common.Branch;
import java.sql.Timestamp;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
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
public class ItemSupply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "supplierId")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;

    private Timestamp createdDate;
    private Timestamp updatedDate;

    @ManyToOne
    @JoinColumn(name = "createdEmployeeId")
    private Employee createdBy;

    @ManyToOne
    @JoinColumn(name = "updatedEmployeeId")
    private Employee updatedEmployee;

    @ManyToOne
    @JoinColumn(name = "branchId")
    private Branch branch;
    private Float unitCost;
    private Boolean active;

    public ItemSupply(Supplier supplier, Item item, Timestamp createdDate, Employee createdBy, Branch branch, Float unitCost, Boolean active) {
        this.supplier = supplier;
        this.item = item;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.branch = branch;
        this.unitCost = unitCost;
        this.active = active;
    }
}