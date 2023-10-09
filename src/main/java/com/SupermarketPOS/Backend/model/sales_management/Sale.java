package com.SupermarketPOS.Backend.model.sales_management;

import com.SupermarketPOS.Backend.model.common.Branch;
import com.SupermarketPOS.Backend.model.customer_management.Customer;

import com.SupermarketPOS.Backend.model.inventory_management.Brand;
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
    @JoinColumn(name = "customerId")
    private Customer customer;


    @ManyToOne
    @JoinColumn(name = "branchId")
    private Branch branch;

    private Timestamp time;

//    @Column(name = "total", precision = 10, scale = 2)
    private Float total;


//    private Double discount;
//    private Integer loyaltyPoints;
    private PaymentType paymentType;

    @OneToMany(mappedBy = "sales")
    private List<SalesItem> salesItemList;


    public Sale(String barcodeNo, Customer customer, Branch branch, Timestamp time, PaymentType paymentType) {
        this.barcodeNo = barcodeNo;
        this.customer = customer;
        this.branch = branch;
        this.time = time;
        this.paymentType = paymentType;
    }

    public Sale(Customer customer, Branch branch, Timestamp time, PaymentType paymentType) {
        this.customer = customer;
        this.branch = branch;
        this.time = time;
        this.paymentType = paymentType;
    }
}
