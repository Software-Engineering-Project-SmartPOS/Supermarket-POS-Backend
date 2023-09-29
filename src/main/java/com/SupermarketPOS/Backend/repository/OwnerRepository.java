package com.SupermarketPOS.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SupermarketPOS.Backend.model.Owner;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Integer> {
    @Query("SELECT o FROM Owner o WHERE o.email = ?1")
    Optional<Owner> findByEmail(String email);

}
