package com.SupermarketPOS.Backend.repository.sales_management;

import com.SupermarketPOS.Backend.model.sales_management.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sale,Integer> {


    Sale findByIdAndBranchId(Integer id, Integer id1);

    List<Sale> findAllByBranchId(Integer id);

}
