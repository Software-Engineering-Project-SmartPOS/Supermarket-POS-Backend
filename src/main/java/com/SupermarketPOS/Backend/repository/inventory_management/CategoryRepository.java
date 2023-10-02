package com.SupermarketPOS.Backend.repository.inventory_management;

import com.SupermarketPOS.Backend.model.inventory_management.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT COUNT(c)>0 FROM Category c WHERE c.name =?1")
    boolean existsByName(String name);

    @Query("SELECT c FROM Category c WHERE c.name =?1")
    Category findByName(String name);
}
