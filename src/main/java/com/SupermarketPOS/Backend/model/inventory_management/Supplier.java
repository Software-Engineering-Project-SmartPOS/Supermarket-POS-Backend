package com.SupermarketPOS.Backend.model.inventory_management;


import com.SupermarketPOS.Backend.model.common.AddressMain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String landPhone;
    private String mobilePhone;
    private String email;

    @OneToOne
    @JoinColumn(name = "supplierAddressId")
    private AddressMain supplierAddress;

    @Column(name = "createdAt", updatable = false, nullable = false)
    private Timestamp createdAt;

    private Boolean active;

    @OneToMany(mappedBy = "supplier")
    private List<ItemSupply> supplyingItems;

    @OneToMany(mappedBy = "supplier")
    private List<PurchaseOrder> purchaseOrderList;

}
