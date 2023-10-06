package com.SupermarketPOS.Backend.model.inventory_management;

import com.SupermarketPOS.Backend.model.common.Branch;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "supplierId")
    private Supplier supplier;
    private String description;

    private LocalDate orderedDate;
    private LocalDate expectedDate;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee createdBy; //change to createdBy


    @ManyToOne
    @JoinColumn(name = "branchId")
    private Branch branch;

    private Float purchaseCost;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<PurchaseOrderItem> purchaseOrderItemList;

    private PurchaseOrderStatus orderStatus = PurchaseOrderStatus.CREATE ;


//    public PurchaseOrder(Supplier supplier, Employee createdBy, String description, Branch branch, PurchaseOrderStatus orderStatus) {
//        this.supplier = supplier;
//        this.createdBy = createdBy;
//        this.description = description;
//        this.branch = branch;
//        this.orderStatus = orderStatus;
//    }

    public PurchaseOrder(Supplier supplier, LocalDate orderedDate, LocalDate expectedDate, Employee createdBy, String description, Branch branch,Float purchaseCost) {
        this.supplier = supplier;
        this.orderedDate = orderedDate;
        this.expectedDate = expectedDate;
        this.createdBy = createdBy;
        this.description = description;
        this.branch = branch;
        this.purchaseCost = purchaseCost;
        this.purchaseCost =0F;
    }
}
