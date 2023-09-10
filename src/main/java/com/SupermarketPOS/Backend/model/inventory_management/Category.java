package com.SupermarketPOS.Backend.model.inventory_management;

import java.sql.Date;
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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Timestamp addedDate;

    private Integer updatedUserId;

    private Timestamp updatedDate;

    @OneToMany(mappedBy = "category")
    private List<Item> itemList;

}

