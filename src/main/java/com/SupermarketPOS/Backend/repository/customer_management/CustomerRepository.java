package com.SupermarketPOS.Backend.repository.customer_management;

import com.SupermarketPOS.Backend.model.customer_management.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query("SELECT COUNT(c) > 0 FROM Customer c WHERE c.telephone = ?1")
    Boolean isTelephoneTaken(String telephone);

    @Query("SELECT COUNT(c) > 0 FROM Customer c WHERE c.email = ?1")
    Boolean isEmailTaken(String email);
}
