package com.SupermarketPOS.Backend.repository.employee_management;

import com.SupermarketPOS.Backend.model.common.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
//    get the address by the given id
    Address getById(Integer id);
}
