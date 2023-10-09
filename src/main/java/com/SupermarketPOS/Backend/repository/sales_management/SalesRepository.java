package com.SupermarketPOS.Backend.repository.sales_management;

import com.SupermarketPOS.Backend.model.sales_management.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sale,Integer> {


}
