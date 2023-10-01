package com.SupermarketPOS.Backend.model.inventory_management;


import com.SupermarketPOS.Backend.model.common.Address;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")}) // Define the unique constraint
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String landPhone;
    private String mobilePhone;
    private String email;

    @OneToOne
    @JoinColumn(name = "supplierAddressId")
    private Address address;

    @Column(name = "createdAt", updatable = false, nullable = false)
    private Timestamp createdAt;
    private Timestamp updatedAt;
//    private Integer BranchId;
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "createdEmployeeId")
    private Employee createdBy;

    @ManyToOne
    @JoinColumn(name = "updatedEmployeeId")
    private Employee updatedEmployee;


    @OneToMany(mappedBy = "supplier")
    private List<ItemSupply> supplyingItems;

    @OneToMany(mappedBy = "supplier")
    private List<PurchaseOrder> purchaseOrderList;

    public Supplier(String name, String landPhone, String mobilePhone, String email, Address supplierAddress, Timestamp createdAt, Employee createdBy, Boolean active) {
        this.name = name;
        this.landPhone = landPhone;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.address = supplierAddress;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.active = active;
    }
}
