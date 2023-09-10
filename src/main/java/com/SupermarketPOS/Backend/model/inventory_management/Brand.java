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
public class
Brand {
    // brand of the items
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;

    private Integer updateUserId;

    private Timestamp updateAt;

    @OneToMany(mappedBy = "brand")
    private List<Item> itemList;


}
