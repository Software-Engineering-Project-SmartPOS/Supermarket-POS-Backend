package com.SupermarketPOS.Backend.model.inventory_management;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Brand {
    // brand of the items
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private Integer updateUserId;
    private Timestamp createAt;
    private Timestamp updateAt;

    @OneToMany(mappedBy = "brand")
    private List<Item> itemList;

    public Brand(String name, String description, Timestamp createAt, Integer updateUserId) {
        this.name = name;
        this.description = description;
        this.updateAt = createAt;
        this.createAt = createAt;
        this.updateUserId= updateUserId;
    }


}
