package com.SupermarketPOS.Backend.repository.inventory_management;

import com.SupermarketPOS.Backend.model.inventory_management.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    @Query("SELECT s FROM Supplier s WHERE s.email =?1")
    Optional<Supplier> findByEmail(String email);
}
