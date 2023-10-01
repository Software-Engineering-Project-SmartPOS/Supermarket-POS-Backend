package com.SupermarketPOS.Backend.model.inventory_management;

import java.sql.Timestamp;
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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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

}

