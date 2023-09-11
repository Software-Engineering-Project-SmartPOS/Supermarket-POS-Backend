package com.SupermarketPOS.Backend.repository.inventory_management;

import com.SupermarketPOS.Backend.model.inventory_management.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BrandRepository extends JpaRepository<Brand,Integer> {
    @Query("SELECT COUNT(b)> 0 FROM Brand b WHERE b.name = ?1")
    boolean isAvailablebyName(String  brandName);
}
