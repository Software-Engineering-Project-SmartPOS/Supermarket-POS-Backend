package com.SupermarketPOS.Backend.repository.customer_management;

import com.SupermarketPOS.Backend.model.customer_management.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress,Integer> {
}
