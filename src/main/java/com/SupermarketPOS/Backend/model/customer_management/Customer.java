package com.SupermarketPOS.Backend.model.customer_management;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.Constraint;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String telephone;
    private String email;
    private CustomerType customer_type;
    private Integer loyalty_id;
    private LocalDateTime created_at;

    public Customer(String name, String telephone, String email ,LocalDateTime created_at) {
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.created_at = created_at;
    }


}
