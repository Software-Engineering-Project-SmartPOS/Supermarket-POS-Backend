package com.SupermarketPOS.Backend.model.customer_management;

import com.SupermarketPOS.Backend.model.common.AddressMain;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.Constraint;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Integer id;
    private String name;
    private String telephone;
    private String email;

    @OneToOne
    @JoinColumn(name = "customerAddressId")

    private CustomerAddress customerAddress;
    private CustomerType customerType;
    private Integer loyaltyId;
    private Timestamp createdAt;
    private Timestamp firstVisited;
    private Timestamp lastVisited;

    public Customer(String name, String telephone, String email ,Timestamp createdAt) {
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.createdAt = createdAt;
        this.firstVisited =createdAt; //first visit allways be the created time
    }

    public Customer(String name, String telephone, String email, CustomerAddress customerAddress, CustomerType customerType, Integer loyaltyId, Timestamp createdAt, Timestamp firstVisited, Timestamp lastVisited) {
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.customerAddress = customerAddress;
        this.customerType = customerType;
        this.loyaltyId = loyaltyId;
        this.createdAt = createdAt;
        this.firstVisited = firstVisited;
        this.lastVisited = lastVisited;
    }

    //    public Customer(String name, String telephone, String email, CustomerAddress customerAddress, CustomerType customerType,  Timestamp createdAt) {
//        this.name = name;
//        this.telephone = telephone;
//        this.email = email;
//        this.customerAddress = customerAddress;
//        this.customerType = customerType;
//        this.createdAt = createdAt;
//        this.firstVisited = createdAt; //first visit allways be the created time
//    }
}
