package com.SupermarketPOS.Backend.model.sales_management;

import com.SupermarketPOS.Backend.model.customer_management.Customer;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
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
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String barcodeNo;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Timestamp time;

    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total;


    private Double discount;
    private Integer loyaltyPoints;
    private PaymentType paymentType;

    @OneToMany(mappedBy = "sales")
    private List<SalesItem> salesItemList;


}
