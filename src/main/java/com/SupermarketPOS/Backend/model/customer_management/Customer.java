package com.SupermarketPOS.Backend.model.customer_management;

import com.SupermarketPOS.Backend.model.common.Address;
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

    @ManyToOne
    @JoinColumn(name = "customerAddressId")
    private Address customerAddress;
    private CustomerType customerType;
    private Integer loyaltyId;
    private Timestamp createdAt;
    private Timestamp firstVisited;
    private Timestamp lastVisited;
    private String password;

    public Customer(String name, String telephone, String email ,Timestamp createdAt) {
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.createdAt = createdAt;
        this.firstVisited =createdAt; //first visit allways be the created time
    }

    public Customer(String name, String telephone, String email, Address customerAddress, CustomerType customerType, Integer loyaltyId, Timestamp createdAt, Timestamp firstVisited, Timestamp lastVisited, String password) {
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.customerAddress = customerAddress;
        this.customerType = customerType;
        this.loyaltyId = loyaltyId;
        this.createdAt = createdAt;
        this.firstVisited = firstVisited;
        this.lastVisited = lastVisited;
        this.password = password;
    }

    public void UpdateNameAndCustomerType(String name, CustomerType customerType){
        // name is not the same and not null
        if (this.name != name && name != null ){
        this.name = name;
        }
        // if the customer Type is not the same and not null
        if (this.customerType != customerType && customerType != null)
        this.customerType = customerType;
    }
}
