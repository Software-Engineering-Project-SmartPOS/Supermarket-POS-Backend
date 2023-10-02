package com.SupermarketPOS.Backend.model.inventory_management;

import java.sql.Timestamp;
import java.util.List;

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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String itemCode;
    private String name;
    private String barcodeNo;
    private String description;

    @ManyToOne
    @JoinColumn(name = "categoryId") // Specify the foreign key column
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brandId")
    private Brand brand;
    private UnitOfMeasure unitOfMeasure;

    @Column(name = "createdAt", updatable = false, nullable = false)
    private Timestamp createdAt;
    @Column(name = "updatedAt")
    private Timestamp updatedAt;
    private Boolean active;
    private Integer reorderLevel;
    private Boolean returnable;

    @ManyToOne
    @JoinColumn(name = "createdEmployeeId")
    private Employee createdBy;

    @ManyToOne
    @JoinColumn(name = "updatedEmployeeId")
    private Employee updatedBy;

    public Item(String itemCode, String name, String barcodeNo, String description, Category category, Brand brand, UnitOfMeasure unitOfMeasure, Timestamp createdAt, Timestamp updatedAt, Boolean active, Integer reorderLevel, Boolean returnable) {
        this.itemCode = itemCode;
        this.name = name;
        this.barcodeNo = barcodeNo;
        this.description = description;
        this.category = category;
        this.brand = brand;
        this.unitOfMeasure = unitOfMeasure;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.active = active;
        this.reorderLevel = reorderLevel;
        this.returnable = returnable;
    }
}

